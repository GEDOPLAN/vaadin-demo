de_gedoplan_demos_vaadin_demo_ui_components_JsHtml5TextFieldExtension = function() {

	var element = this.getElement(this.getParentId());

	this.onStateChange = function() {
		element.type = this.getState().inputType;
	};

	var self = this;
	element.onchange = function() {
		self.wertGeandert(mycomponent.getValue());
	}

};