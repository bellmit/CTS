/*
 * Created on Jan 9, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.manager;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author C7500378
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewStructure implements Serializable {

    String className = null;
    ArrayList children = null;
    String id = null;
    HashMap facets = null;

    public ViewStructure(UIComponent component) {
        this.id = component.getId();
        className = component.getClass().getName();
    }

    public void addChild(ViewStructure treeStruct) {
        if (children == null) {
            children = new ArrayList();
        }
        children.add(treeStruct);
    }

    public Iterator getChildren() {
        if (children != null) {
            return (children.iterator());
        } 
        return (Collections.EMPTY_LIST.iterator());
    }

    public void addFacet(String facetName, ViewStructure treeStruct) {
        if (facets == null) {
            facets = new HashMap();
        }
        facets.put(facetName, treeStruct);
    }


    public ViewStructure getViewStructureForFacet(String facetName) {
        if (facets != null) {
            return ((ViewStructure) (facets.get(facetName)));
        }
        return null;
    }

    public Iterator getFacetNames() {
        if (facets != null) {
            return (facets.keySet().iterator());
        }
        return (Collections.EMPTY_LIST.iterator());
    }


    public UIComponent createComponent() {
        UIComponent component = null;
       	Class c;
		try {
			c = this.getClass().getClassLoader().loadClass(this.className);
			component = ((UIComponent) c.newInstance());
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("error message "+exception.getMessage());
			//do something later
		}
			
        component.setId(this.id);
        return component;
    }
    
    public void buildStructure(FacesContext context,UIComponent component, ViewStructure treeStructure) {

        
        Iterator children = component.getChildren().iterator();
        while (children.hasNext()) {
            UIComponent child = (UIComponent) children.next();
            if (!child.isTransient()) {
                ViewStructure structureChild = new ViewStructure(child);
                treeStructure.addChild(structureChild);
                buildStructure(context, child, structureChild);
            }
        }

        Iterator facets = component.getFacets().keySet().iterator();
        while (facets.hasNext()) {
            String facetName = (String) facets.next();
            UIComponent facetComponent = (UIComponent) component.getFacets().
                get(facetName);
            if (!(facetComponent.isTransient())) {
                ViewStructure treeStructureFacet = new ViewStructure(facetComponent);
                treeStructure.addFacet(facetName, treeStructureFacet);
                // process children of facet.
                buildStructure(context, facetComponent, treeStructureFacet);
            }
        }
    }
    public void restoreStructure(ViewStructure structure, UIComponent component) {
		Iterator children = structure.getChildren();
		while (children.hasNext()) {
			ViewStructure child = (ViewStructure) children.next();
			UIComponent comp = child.createComponent();
			component.getChildren().add(comp);
			restoreStructure(child, comp);
		}

		Iterator facets = structure.getFacetNames();
		while (facets.hasNext()) {
			String facetName = (String) facets.next();
			ViewStructure facetStructure = structure.getViewStructureForFacet(facetName);
			UIComponent facetComponent = facetStructure.createComponent();
			component.getFacets().put(facetName, facetComponent);
			restoreStructure(facetStructure, facetComponent);
		}
	}

    
}
