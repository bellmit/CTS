/*
 * Created on Dec 18, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.contactmanagement.view.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeState;

import com.acs.enterprise.common.program.administration.application.exception.ReferenceServiceRequestException;
import com.acs.enterprise.common.program.administration.application.exception.SystemListNotFoundException;
import com.acs.enterprise.common.program.administration.common.delegate.ReferenceServiceDelegate;
import com.acs.enterprise.common.program.administration.common.vo.InputCriteria;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataListVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceDataSearchVO;
import com.acs.enterprise.common.program.administration.common.vo.ReferenceServiceVO;
import com.acs.enterprise.common.program.administration.util.helper.FunctionalAreaConstants;
import com.acs.enterprise.common.program.administration.util.helper.ProgramConstants;
import com.acs.enterprise.common.program.administration.util.helper.ReferenceServiceDataConstants;
import com.acs.enterprise.common.program.benefitadministration.application.exception.LineOfBusinessNotFoundException;
import com.acs.enterprise.common.program.benefitadministration.common.delegate.LineOfBusinessDelegate;
import com.acs.enterprise.common.program.benefitadministration.common.domain.LineOfBusiness;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyCreateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyDuplicateBusinessException;
import com.acs.enterprise.common.program.contactmanagement.application.exception.LOBHierarchyFetchBusinessException;
import com.acs.enterprise.common.program.contactmanagement.common.delegate.ContactMaintenanceDelegate;
import com.acs.enterprise.common.program.contactmanagement.common.domain.LineOfBusinessHierarchy;
import com.acs.enterprise.common.program.contactmanagement.util.helper.ContactManagementConstants;
import com.acs.enterprise.common.program.contactmanagement.view.helper.LobHierarchyDOConverter;
import com.acs.enterprise.common.program.contactmanagement.view.vo.LobHierarchyVO;
import com.acs.enterprise.common.util.logger.EnterpriseLogFactory;
import com.acs.enterprise.common.util.logger.EnterpriseLogger;

/**
 * @author Wipro TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class LobHierarchyPlanTree
{

    /** holds HtmlTree. */
    private HtmlTree tree;

    /** holds TreeModel. */
    private TreeModel model;

    /** holds TreeNode. */
    private TreeNode treeNode;
    
    //commented for unused variables
    //private boolean flag = true;

    /**
     * EnterpriseLogger Name for Logging.
     */
    private transient EnterpriseLogger logger = EnterpriseLogFactory
            .getLogger(LobHierarchyPlanTree.class);

    /**
     * constructor LobHierarchyPlanTree
     */
    public LobHierarchyPlanTree()
    {
        super();
        logger.debug("Inside constructor LobHierarchyPlanTree");
    }
    
    private boolean treeFlag = true;

    /**
     * @return Returns the model.
     */
    public TreeModel getModel()
    {
    	
    	if(this.isTreeFlag())
    	{	
    		
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        TreeNode[] treeNode1;

        logger
                .info("<< LobHierarchyPlanTree >>inside <<getModel>> method begin >>");
        
        try {
			treeNode1 = getTreeData();
			TreeNode base = new TreeNodeBase("base",
			        ContactManagementConstants.EMPTY_STRING, false);
			int len = treeNode1.length;
			for (int i = 0; i < len; i++)
			{
			    base.getChildren().add(treeNode1[i]);
			}
			model = new TreeModelBase(base);

			if (session.getAttribute(ContactManagementConstants.MODEL_OBJECT_NAME)==null ||
					session.getAttribute(ContactManagementConstants.LOB_PLAN_TREE_STATE) == null)
			{
				
			    session.setAttribute(ContactManagementConstants.MODEL_OBJECT_NAME, model);

			}
			else
			{
				
			    model
			            .setTreeState((TreeState) session
			                    .getAttribute(ContactManagementConstants.LOB_PLAN_TREE_STATE));
			    /*    
			            
			    session.setAttribute(ContactManagementConstants.MODEL_OBJECT_NAME,
			            model);
			     */
			    
			}
		} 
        catch (Exception e) 
		{
			
			logger
            .error("<< LobHierarchyPlanTree >> Exception >>"+e.getMessage());
			
		}
        this.setTreeFlag(false);
    	}
        return model;
    }

    /**
     * This method will Fetch all Childs and Parents objects and to build the
     * Tree Structure.
     * 
     * @return TreeNode.
     */
    public TreeNodeBase[] getTreeData()
    {
    	List list = getLobReferenceData();
        TreeNodeBase[] lob = null;

        LineOfBusinessHierarchy lobHierarchy = null;
        List childsLOb = null;
        String ind = null;
        int i = 0;

        if (list != null)
        {
            int listSize = list.size();
            lob = new TreeNodeBase[listSize];

            int count = 0;
            for (i = 0; i < listSize; i++)
            {
                SelectItem si = (SelectItem) list.get(i);
                String codeLOB = si.getValue().toString();
                String lobDescription = si.getLabel();
                //"FindBugs" issue fixed
                //Integer levelNumber = new Integer(1);
                Integer levelNumber = Integer.valueOf(1);
                lobHierarchy = lobCodetoLobHirachy(codeLOB);
                logger.debug(" ******* getTreeData codeLOB : "+codeLOB);
                logger.debug(" ******* getTreeData lobDescription : "+lobDescription);
                if (lobHierarchy == null)
                {
                	logger.debug(" ******* getTreeData lobHierarchy null : "+codeLOB);
                    LobHierarchyVO lobHierarchyVO = new LobHierarchyVO();
                    lobHierarchyVO.setLobCode(codeLOB);
                    lobHierarchyVO.setLobDesc(lobDescription);
                    LineOfBusinessHierarchy lineOfBusinessHierarchy = new LobHierarchyDOConverter()
                            .convertVOtoDO(lobHierarchyVO);
                    lineOfBusinessHierarchy
                            .setLobHierarchyLevelNumber(levelNumber);

                    LineOfBusinessDelegate lineOfBusinessDelegate = new LineOfBusinessDelegate();

                    try
                    {
                        LineOfBusiness lineOfBusiness = lineOfBusinessDelegate
                                .getLineOfBusinessDetail(lobHierarchyVO
                                        .getLobCode());
                        lineOfBusinessHierarchy
                                .setLineOfBusiness(lineOfBusiness);

                        new ContactMaintenanceDelegate()
                                .createLOBHierarchy(lineOfBusinessHierarchy);

                    }
                    catch (LineOfBusinessNotFoundException e)
                    {
                        logger.error(" LobHierarchyPlanTree getTreeData LineOfBusinessNotFoundException "+e.getMessage(), e);
                    }
                    catch (LOBHierarchyDuplicateBusinessException e)
                    {
                        logger.error(" LobHierarchyPlanTree getTreeData LOBHierarchyDuplicateBusinessException "+e.getMessage(), e);
                    }
                    catch (LOBHierarchyCreateBusinessException e)
                    {
                        logger.error(" LobHierarchyPlanTree getTreeData LOBHierarchyCreateBusinessException "+e.getMessage(), e);
                    }

                }
                else
                {
                	logger.debug(" ******* getTreeData lobHierarchy Not null : "+codeLOB);
                    //lobHierarchy = lobCodetoLobHirachy(codeLOB);

                    if (lobHierarchy != null 
                    		&& lobHierarchy.getLobHierarchyLevelNumber() !=null
                    		&& lobHierarchy.getLineOfBusinessHierarchySK() != null 
							&& lobHierarchy.getLineOfBusiness() != null
							&& lobHierarchy.getLineOfBusiness().getLobCode()!= null
							&& lobHierarchy.getLobHierarchyDescription() != null)
                    {

                        ind = codeLOB
                                + ContactManagementConstants.HYPHEN_SEPARATOR
                                + lobHierarchy.getLobHierarchyLevelNumber()
                                + ContactManagementConstants.HYPHEN_SEPARATOR
                                + lobHierarchy.getLineOfBusinessHierarchySK();
                        lob[count] = new TreeNodeBase("lobs", lobHierarchy
                                .getLineOfBusiness().getLobCode()
                                + ContactManagementConstants.SPACE_STRING
                                + lobHierarchy.getLobHierarchyDescription(),
                                ind, false);

                        Long parentSK = lobHierarchy
                                .getLineOfBusinessHierarchySK();
                        logger.debug(" ******* getTreeData parentSK : "+parentSK);
                        childsLOb = callChilds(parentSK);
                        if (parentSK != null && childsLOb != null && childsLOb.size()>0)
                        		//&& Boolean.TRUE.equals(availChilds(parentSK)))
                        {
							/* for ESPRD00711655 defect*/
							sortHierarchyInDescOrder(childsLOb);
                            addLobHierarchyChilds(codeLOB, childsLOb, 2,
                                    lob[count]);
                        }
                    }
                    ++count;

                }

            }
            
            //          Remove Tree Node 
			if(count>0 && lob != null && listSize > count)
			{
				logger.debug(" removeTreeNodeBase Count : "+count);
				logger.debug(" removeTreeNodeBase listSize : "+listSize);
				lob=removeTreeNodeBase(count,lob);
			}
        }
        
        return lob;
    }

    /**
     * This operation is used to add the childs to a node.
     * 
     * @param lobCode lobcode of string type
     * @param lobHierarchyList lobHierarchyList of List
     * @param levelNum levelNum of int type
     * @param parentNode parentNode of TreeNodeBase type
     */
    private void addLobHierarchyChilds(String lobCode, List lobHierarchyList,
            int levelNum, TreeNodeBase parentNode)
    {
       
        String level = null;
        LineOfBusinessHierarchy lobHierarchy = null;
        if (levelNum == ContactManagementConstants.TWO.intValue())
        {
            level = "reports";
        }
        if (levelNum == ContactManagementConstants.THREE.intValue())
        {
            level = "business";
        }
        if (levelNum == ContactManagementConstants.FOUR.intValue())
        {
            level = "depart";
        }
        logger.debug(" ******* addLobHierarchyChilds lobCode : "+lobCode);
        logger.debug(" ******* addLobHierarchyChilds levelNum : "+levelNum);
        logger.debug(" ******* addLobHierarchyChilds level : "+level);
        if (lobHierarchyList != null && levelNum<=4)
        {
            int lobHierarchyListSize = lobHierarchyList.size();
            for (int l = 0; l < lobHierarchyListSize; ++l)
            {
                lobHierarchy = (LineOfBusinessHierarchy) lobHierarchyList
                        .get(l);

                if (lobHierarchy != null 
                		&& level !=null
                		&& lobCode != null
                		&& lobHierarchy.getLineOfBusinessHierarchySK() != null 						
						&& lobHierarchy.getLobHierarchyDescription() != null)
                {
                	TreeNodeBase currentNode = getTreeNodeBase(lobCode,
                			lobHierarchy, level);

                	parentNode.getChildren().add(currentNode);

                	Long parentSK = lobHierarchy.getLineOfBusinessHierarchySK();
                	if (parentSK != null && levelNum<=4)
                	//	&& Boolean.TRUE.equals(availChilds(parentSK)) )
                	{
                		List childsLob = null;
                		childsLob = callChilds(parentSK);
                		if(childsLob!=null){
							/* for ESPRD00711655 defect*/
                			sortHierarchyInDescOrder(childsLob);
                			addLobHierarchyChilds(lobCode, childsLob, levelNum + 1,
                				currentNode);
                		}
                	}
                }
            }

        }
        
    }

    /**
     * This operation is used to get the Node base for respective node.
     * 
     * @param lobCode lobcode of string type
     * 
     * @param lobHierarchy lobHierarchy of LineOfBusinessHierarchy type
     * 
     * @param level level of string type
     * 
     * @return TreeNodeBase
     */
    private TreeNodeBase getTreeNodeBase(String lobCode,
            LineOfBusinessHierarchy lobHierarchy, String level)
    {
     
        String ind = null;
        ind = lobCode + ContactManagementConstants.HYPHEN_SEPARATOR
                + lobHierarchy.getLobHierarchyLevelNumber()
                + ContactManagementConstants.HYPHEN_SEPARATOR
                + lobHierarchy.getLineOfBusinessHierarchySK();
        
        return new TreeNodeBase(level, lobHierarchy
                .getLobHierarchyDescription(), ind, false);
    }

    /**
     * @param model
     *            The model to set.
     */
    public void setModel(TreeModel model)
    {
        this.model = model;
    }

    /**
     * @return Returns the tree.
     */
    public HtmlTree getTree()
    {
        return tree;
    }

    /**
     * @param tree
     *            The tree to set.
     */
    public void setTree(HtmlTree tree)
    {
        this.tree = tree;
    }

    /**
     * @return Returns the treeNode.
     */
    public TreeNode getTreeNode()
    {
        return treeNode;
    }

    /**
     * @param treeNode
     *            The treeNode to set.
     */
    public void setTreeNode(TreeNode treeNode)
    {

        this.treeNode = treeNode;
    }

    /**
     * This method is used to retreive valid values in the Line Of Business
     * 
     * @return List
     */
    public final List getLobReferenceData()
    {
        logger.info("getLobReferenceData");
      
        long entryTime = System.currentTimeMillis();

        List referenceList = new ArrayList();
        Map referenceMap = null;

        int referenceListSize = 0;
        List lobList = new ArrayList();

        ReferenceDataSearchVO referenceDataSearch = new ReferenceDataSearchVO();
        ReferenceServiceDelegate referenceServiceDelegate = new ReferenceServiceDelegate();
        //FindBugs issue Fixed
       // ReferenceDataListVO referenceDataListVO = new ReferenceDataListVO();
        ReferenceDataListVO referenceDataListVO = null;

        InputCriteria inputCriteriaLob = new InputCriteria();
        inputCriteriaLob.setFunctionalArea(FunctionalAreaConstants.REFERENCE);
        inputCriteriaLob.setElementName(ReferenceServiceDataConstants.R_LOB_CD);

        referenceList.add(inputCriteriaLob);

        referenceDataSearch.setInputList(referenceList);

        try
        {
            referenceDataListVO = referenceServiceDelegate
                    .getReferenceData(referenceDataSearch);

            if(referenceDataListVO!=null){
            	referenceMap = referenceDataListVO.getResponseMap();
            }
            
            //"FindBugs" issue fixed
            /*referenceList = (List) referenceMap
                    .get(FunctionalAreaConstants.REFERENCE
                            + ProgramConstants.HASH_SEPARATOR
                            + ReferenceServiceDataConstants.R_LOB_CD);*/
            if(referenceMap!=null)
            {
            	referenceList = (List) referenceMap
                .get(FunctionalAreaConstants.REFERENCE
                        + ProgramConstants.HASH_SEPARATOR
                        + ReferenceServiceDataConstants.R_LOB_CD);
            }
            referenceListSize = referenceList.size();

            logger.debug("size of lob list" + referenceListSize);
            for (int i = 0; i < referenceListSize; i++)
            {
                ReferenceServiceVO refVo = (ReferenceServiceVO) referenceList
                        .get(i);
                logger.debug((i + 1) + " vv code = "
                        + refVo.getValidValueCode());
                logger.debug((i + 1) + " vv description = "
                        + refVo.getShortDescription());

                lobList.add(new SelectItem(refVo.getValidValueCode(), refVo
                        .getShortDescription()));

            }

        }
        catch (ReferenceServiceRequestException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (SystemListNotFoundException e)
        {
            logger.error(e.getMessage(), e);
        }
        long exitTime = System.currentTimeMillis();
        logger.info("LobHierarchyPlanTree" + "#" + "getLobReferenceData" + "#"
                + (exitTime - entryTime));
        
        return lobList;
    }

    /**
     * This method will fetch Parent Record (Level Number 1) Based On the
     * LobCode.
     * 
     * @param lobCode :
     * @return LineOfBusinessHierarchy
     */
    private LineOfBusinessHierarchy lobCodetoLobHirachy(String lobCode)
    {
       
        LineOfBusinessHierarchy lineOfBusinessHierarchy = new LineOfBusinessHierarchy();

        try
        {

            lineOfBusinessHierarchy = new ContactMaintenanceDelegate()
                    .getLobHierarchyRoot(lobCode);

        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error("LineOfBusinessNotFoundException-->" + e.getMessage(),
                    e);
        }
        
        return lineOfBusinessHierarchy;
    }

    /**
     * This method will fetch the Child Records Based On the ParentSK.
     * 
     * @param parentSK :
     * @return List
     */

    private List callChilds(Long parentSK)
    {
       
        List list = new ArrayList();

        try
        {
            list = new ContactMaintenanceDelegate()
                    .getLOBHierarchyDetails(parentSK);
        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error("LineOfBusinessNotFoundException-->" + e.getMessage(),
                    e);
        }
        
        return list;
    }

    /**
     * This method will check whether the Child Records are available or not
     * Based On the ParentSK.
     * 
     * @param parentSK :
     * @return List
     */
    private Boolean availChilds(Long parentSK)
    {
       
        Boolean flag = Boolean.FALSE;

        try
        {
            flag = new ContactMaintenanceDelegate().isChildExists(parentSK);

        }
        catch (LOBHierarchyFetchBusinessException e)
        {
            logger.error("LineOfBusinessNotFoundException-->" + e.getMessage(),
                    e);
        }
        
        return flag;
    }
    
    private TreeNodeBase[] removeTreeNodeBase(int count,TreeNodeBase[] lob ){
		TreeNodeBase[] lobTreeNode = null; 
		
		if(count>0 && lob!=null && lob.length>count){
			lobTreeNode= new TreeNodeBase[count];
			for(int i=0;i<count;i++){
				lobTreeNode[i]=lob[i];
			}			
		}
		else{
			lobTreeNode= lob;
		}
		
		return lobTreeNode;
	}

	/**
	 * @return Returns the treeFlag.
	 */
	public boolean isTreeFlag() {
		return treeFlag;
	}
	/**
	 * @param treeFlag The treeFlag to set.
	 */
	public void setTreeFlag(boolean treeFlag) {
		this.treeFlag = treeFlag;
	}

	 /**
     * This operation is used to sort the 
     *  listed hierarchy in alphabetic description order.
     *  for ESPRD00711655 defect
     * @param childsLOb hierarchy list
     */
    public void sortHierarchyInDescOrder(List childsLOb){
    	
    	Comparator compareDesc=new Comparator(){

			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				LineOfBusinessHierarchy lobHierarchy1=null;
				LineOfBusinessHierarchy lobHierarchy2=null;
				
				if(o1 instanceof LineOfBusinessHierarchy 
						&& o2 instanceof LineOfBusinessHierarchy){
					
					lobHierarchy1=(LineOfBusinessHierarchy)o1;
					lobHierarchy2=(LineOfBusinessHierarchy)o2;
								
					if(lobHierarchy1.getLobHierarchyDescription()==null){
						lobHierarchy1.setLobHierarchyDescription(ContactManagementConstants.EMPTY_STRING);
					}
					if(lobHierarchy2.getLobHierarchyDescription()==null){
						lobHierarchy2.setLobHierarchyDescription(ContactManagementConstants.EMPTY_STRING);
					}
					
					return lobHierarchy1.getLobHierarchyDescription()
						.compareToIgnoreCase(lobHierarchy2.getLobHierarchyDescription());
					
				}else
				return 0;
			}};
			
		Collections.sort(childsLOb,compareDesc);
    	
    }
}
