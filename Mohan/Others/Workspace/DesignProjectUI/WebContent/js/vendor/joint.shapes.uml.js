/*! JointJS v0.9.0 - JavaScript diagramming library  2014-05-13 


This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
if (typeof exports === 'object') {

    var joint = {
        util: require('../src/core').util,
        shapes: {
            basic: require('./joint.shapes.basic')
        },
        dia: {
            ElementView: require('../src/joint.dia.element').ElementView,
            Link: require('../src/joint.dia.link').Link
        }
    };
    var _ = require('lodash');
}

joint.shapes.uml = {};

joint.shapes.uml.Class = joint.shapes.basic.Generic.extend({

    markup: [
        '<g class="rotatable">',
          '<g class="scalable">',
            '<rect class="uml-class-name-rect"/><rect class="uml-class-attrs-rect"/><rect class="uml-class-methods-rect"/>',
          '</g>',
          '<text class="uml-class-name-text"/><text class="uml-class-attrs-text"/><text class="uml-class-methods-text"/>',
        '</g>'
    ].join(''),

    defaults: joint.util.deepSupplement({

        type: 'uml.Class',

        attrs: {
            rect: { 'width': 200 },

            '.uml-class-name-rect': { 'stroke': 'black', 'stroke-width': 2, 'fill': '#FFFFCC' },
            '.uml-class-attrs-rect': { 'stroke': 'black', 'stroke-width': 2, 'fill': '#CCFFCC' },
            '.uml-class-methods-rect': { 'stroke': 'black', 'stroke-width': 2, 'fill': '#CCFFCC' },

            '.uml-class-name-text': {
                'ref': '.uml-class-name-rect', 'ref-y': .5, 'ref-x': .5, 'text-anchor': 'middle', 'y-alignment': 'middle', 'font-weight': 'bold',
                'fill': 'black', 'font-size': 12, 'font-family': 'Times New Roman'
            },
            '.uml-class-attrs-text': {
                'ref': '.uml-class-attrs-rect', 'ref-y': 5, 'ref-x': 5,
                'fill': 'black', 'font-size': 12, 'font-family': 'Times New Roman'
            },
            '.uml-class-methods-text': {
                'ref': '.uml-class-methods-rect', 'ref-y': 5, 'ref-x': 5,
                'fill': 'black', 'font-size': 12, 'font-family': 'Times New Roman'
            }
        },

        name: [],
        attributes: [],
        methods: []

    }, joint.shapes.basic.Generic.prototype.defaults),

    initialize: function() {

        _.bindAll(this, 'updateRectangles');

        this.on('change:name change:attributes change:methods', function() {
            this.updateRectangles();
	    this.trigger('uml-update');
        });

        this.updateRectangles();

        joint.shapes.basic.Generic.prototype.initialize.apply(this, arguments);
    },

    getClassName: function() {
        return this.get('name');
    },

    updateRectangles: function() {

        var attrs = this.get('attrs');

        var rects = [
            { type: 'name', text: this.getClassName() },
            { type: 'attrs', text: this.get('attributes') },
            { type: 'methods', text: this.get('methods') }
        ];

        var offsetY = 0;

        _.each(rects, function(rect) {

            var lines = _.isArray(rect.text) ? rect.text : [rect.text];
	    var rectHeight = lines.length * 20 + 20;

            attrs['.uml-class-' + rect.type + '-text'].text = lines.join('\n');
            attrs['.uml-class-' + rect.type + '-rect'].height = rectHeight;
            attrs['.uml-class-' + rect.type + '-rect'].transform = 'translate(0,'+ offsetY + ')';

            offsetY += rectHeight;
        });
    }

});

joint.shapes.uml.ClassView = joint.dia.ElementView.extend({

    initialize: function() {

        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

	this.model.on('uml-update', _.bind(function() {
	    this.update();
	    this.resize();
	}, this));
    }
});

joint.shapes.uml.Abstract = joint.shapes.uml.Class.extend({

    defaults: joint.util.deepSupplement({
        type: 'uml.Abstract',
        attrs: {
            '.uml-class-name-rect': { fill : '#e74c3c' },
            '.uml-class-attrs-rect': { fill : '#c0392b' },
            '.uml-class-methods-rect': { fill : '#c0392b' }
        }
    }, joint.shapes.uml.Class.prototype.defaults),

    getClassName: function() {
        return ['<<Abstract>>', this.get('name')];
    }

});
joint.shapes.uml.AbstractView = joint.shapes.uml.ClassView;

joint.shapes.uml.Interface = joint.shapes.uml.Class.extend({

    defaults: joint.util.deepSupplement({
        type: 'uml.Interface',
        attrs: {
            '.uml-class-name-rect': { fill : '#E6CCFF' },
            '.uml-class-attrs-rect': { fill : '#9999FF' },
            '.uml-class-methods-rect': { fill : '#9999FF' }
        }
    }, joint.shapes.uml.Class.prototype.defaults),

    getClassName: function() {
        return ['<<Interface>>', this.get('name')];
    }

});
joint.shapes.uml.InterfaceView = joint.shapes.uml.ClassView;

joint.shapes.uml.Generalization = joint.dia.Link.extend({
    defaults: {
        type: 'uml.Generalization',
        attrs: { '.marker-target': { d: 'M 20 0 L 0 10 L 20 20 z', fill: 'white' }}
    }
});

joint.shapes.uml.Implementation = joint.dia.Link.extend({
    defaults: {
        type: 'uml.Implementation',
        attrs: {
            '.marker-target': { d: 'M 20 0 L 0 10 L 20 20 z', fill: 'white' },
            '.connection': { 'stroke-dasharray': '3,3' }
        }
    }
});

joint.shapes.uml.Aggregation = joint.dia.Link.extend({
    defaults: {
        type: 'uml.Aggregation',
        attrs: { '.marker-target': { d: 'M 40 10 L 20 20 L 0 10 L 20 0 z', fill: 'white' }}
    }
});

joint.shapes.uml.Composition = joint.dia.Link.extend({
    defaults: {
        type: 'uml.Composition',
        attrs: { '.marker-target': { d: 'M 40 10 L 20 20 L 0 10 L 20 0 z', fill: 'black' }}
    }
});

joint.shapes.uml.Association = joint.dia.Link.extend({
    defaults: { type: 'uml.Association' }
});

// Statechart

joint.shapes.uml.State = joint.shapes.basic.Generic.extend({

    markup: [
        '<g class="rotatable">',
          '<g class="scalable">',
            '<rect/>',
          '</g>',
          '<path/><text class="uml-state-name"/><text class="uml-state-events"/>',
        '</g>'
    ].join(''),

    defaults: joint.util.deepSupplement({

        type: 'uml.State',

        attrs: {
            rect: { 'width': 200, 'height': 200, 'fill': '#ecf0f1', 'stroke': '#bdc3c7', 'stroke-width': 3, 'rx': 10, 'ry': 10 },
            path: { 'd': 'M 0 20 L 200 20', 'stroke': '#bdc3c7', 'stroke-width': 2 },
            '.uml-state-name': {
                'ref': 'rect', 'ref-x': .5, 'ref-y': 5, 'text-anchor': 'middle',
                'font-family': 'Courier New', 'font-size': 14, fill: '#000000'
            },
            '.uml-state-events': {
                'ref': 'path', 'ref-x': 5, 'ref-y': 5,
                'font-family': 'Courier New', 'font-size': 14, fill: '#000000'
            }
        },

        name: 'State',
        events: []

    }, joint.shapes.basic.Generic.prototype.defaults),

    initialize: function() {

        _.bindAll(this, 'updateEvents', 'updatePath');

        this.on({
            'change:name': function() { this.updateName(); this.trigger('change:attrs'); },
            'change:events': function() { this.updateEvents(); this.trigger('change:attrs'); },
            'change:size': this.updatePath
        });

        this.updateName();
        this.updateEvents();
        this.updatePath();

        joint.shapes.basic.Generic.prototype.initialize.apply(this, arguments);
    },

    updateName: function() {
        this.get('attrs')['.uml-state-name'].text = this.get('name');
    },

    updateEvents: function() {
        this.get('attrs')['.uml-state-events'].text = this.get('events').join('\n');
    },

    updatePath: function() {
        this.get('attrs')['path'].d = 'M 0 20 L ' + this.get('size').width + ' 20';
    }

});

joint.shapes.uml.StartState = joint.shapes.basic.Circle.extend({

    defaults: joint.util.deepSupplement({

        type: 'uml.StartState',
        attrs: { circle: { 'fill': '#34495e', 'stroke': '#2c3e50', 'stroke-width': 2, 'rx': 1 }}

    }, joint.shapes.basic.Circle.prototype.defaults)

});

joint.shapes.uml.EndState = joint.shapes.basic.Generic.extend({

    markup: '<g class="rotatable"><g class="scalable"><circle class="outer"/><circle class="inner"/></g></g>',

    defaults: joint.util.deepSupplement({

        type: 'uml.EndState',
        size: { width: 20, height: 20 },
        attrs: {
            'circle.outer': {
                transform: 'translate(10, 10)',
                r: 10,
                fill: 'white',
                stroke: '#2c3e50'
            },

            'circle.inner': {
                transform: 'translate(10, 10)',
                r: 6,
                fill: '#34495e'
            }
        }

    }, joint.shapes.basic.Generic.prototype.defaults)

});

joint.shapes.uml.Transition = joint.dia.Link.extend({
    defaults: {
        type: 'uml.Transition',
        attrs: {
            '.marker-target': { d: 'M 10 0 L 0 5 L 10 10 z', fill: '#34495e', stroke: '#2c3e50' },
            '.connection': { stroke: '#2c3e50' }
        }
    }
});
joint.shapes.uml.UseCase = joint.shapes.basic.Generic.extend({

    markup: '<g class="rotatable"><g class="scalable"><ellipse class="uml-usecase-name-ellipse"/></g><text class="uml-usecase-name-text"/></g>',

    defaults: joint.util.deepSupplement({

        type: 'uml.UseCase',
        size: { width: 100, height: 50 },
        attrs: {
            'ellipse': {
                transform: 'translate(50, 25)'
            },
            '.uml-usecase-name-ellipse': {
                stroke: '#D35400', 'stroke-width': 2,
                cx: 0, cy: 0, rx: 50, ry: 25,
                fill: '#E67E22'
            },            
            ".uml-usecase-name-text": {
            	 'ref': '.uml-usecase-name-ellipse', 'ref-y': .5, 'ref-x': .5, 'text-anchor': 'middle', 'y-alignment': 'middle', 'font-weight': 'bold',
                 'fill': 'black', 'font-size': 12, 'font-family': 'Times New Roman'
             }
         },
         name:''

     }, joint.shapes.basic.Generic.prototype.defaults),
    
     initialize: function() {

         _.bindAll(this, 'updateName');

         this.on('change:name ', function() {
             this.updateName();
             this.trigger('uml-update');
         });

         this.updateName();

         joint.shapes.basic.Generic.prototype.initialize.apply(this, arguments);
     },
     getName: function() {
         return this.get('name');
     },
     updateName: function() {

         var attrs = this.get('attrs');
         attrs['.uml-usecase-name-text'].text=this.getName();
         attrs['.uml-usecase-name-ellipse'].width=this.getName().length+10;
     }
 });
joint.shapes.uml.UseCaseView = joint.dia.ElementView.extend({

    initialize: function() {

        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

	this.model.on('uml-update', _.bind(function() {
	    this.update();
	    this.resize();
	}, this));
    }
});
joint.shapes.uml.Actor = joint.shapes.basic.Generic.extend({
	 markup: '<g class="rotatable"><g class="scalable"><circle  class="uml-usecase-actor-circle"/><path class="uml-uc-vertical-line"/><path class="uml-uc-horizontal-line"/><path class="uml-uc-leftangle-line"/><path class="uml-uc-rightangle-line"/></g><text class="uml-usecase-actor-text"/></g>',
	 defaults: joint.util.deepSupplement({
	        type: 'uml.Actor',
	        size: { width: 50, height: 50 },
	        attrs:{ 
	        	 	
	             ".uml-usecase-actor-circle":{	            	 
	                     transform: 'translate(60, 60)',
	                     r: 30,
	                     fill: 'white',
	                     stroke: '#2c3e50'	                 
	             },
	             ".uml-uc-vertical-line":{
	            	 d:"M 60 85 L 60 200",
	            	 fill:'#fcfcfc',
	            	 stroke: '#2c3e50'
	             },
	             ".uml-uc-horizontal-line":{
	            	 d:"M 40 135 L 80 135",
	            	 fill:'#fcfcfc',
	            	 stroke: '#2c3e50'
	             },
	             ".uml-uc-leftangle-line":{
	            	 d:"M 60 200 L 40 250",
	            	 fill:'#fcfcfc',
	            	 stroke: '#2c3e50'
	             },
	             ".uml-uc-rightangle-line":{
	            	 d:"M 60 200 L 80 250",
	            	 fill:'#fcfcfc',
	            	 stroke: '#2c3e50'
	             },
	            ".uml-usecase-actor-text": {
	            	 'ref': '.uml-usecase-actor-circle', 'ref-y': 60, 'ref-x': 25, 'text-anchor': 'middle', 'y-alignment': 'middle', 'font-weight': 'bold',
	                 'fill': 'black', 'font-size': 12, 'font-family': 'Times New Roman'
	             }
	        },
	        actorName:''
	    },  joint.shapes.basic.Generic.prototype.defaults),
	    initialize: function() {
	         _.bindAll(this, 'updateActorName');

	         this.on('change:actorName ', function() {
	             this.updateActorName();
	             this.trigger('uml-update');
	         });

	         this.updateActorName();

	         joint.shapes.basic.Generic.prototype.initialize.apply(this, arguments);
	     },
	     getActorName: function() {
	         return this.get('actorName');
	     },
	     updateActorName: function() {

	         var attrs = this.get('attrs');
	         attrs['.uml-usecase-actor-text'].text=this.getActorName();
	     }
});
joint.shapes.uml.ActorView = joint.dia.ElementView.extend({

    initialize: function() {

        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

	this.model.on('uml-update', _.bind(function() {
	    this.update();
	    this.resize();
	}, this));
    }
});
joint.shapes.uml.UseCaseLink = joint.dia.Link.extend({

    defaults: joint.util.deepSupplement({

        attrs: { '.marker-target': { d: 'M 10 0 L 0 5 L 10 10 z' }}
        
    }, joint.dia.Link.prototype.defaults)
});
if (typeof exports === 'object') {

    module.exports = joint.shapes.uml;
}
