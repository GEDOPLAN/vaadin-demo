var components = components || {};

components.JsHtml5TextField = function(element) {
	element.innerHTML = "<input type ='text' />";
	input = element.getElementsByTagName("input")[0];

	this.getInputType = function() {
		return input.type;
	};
	this.setInputType = function(type) {
		input.type = type;
	};

	this.getValue = function() {
		input.value;
	};

	// Default implementation of the click handler
	this.wertGeaendert = function() {
		alert("Error: Must implement click() method");
	};

	// Set up button click
	var self = this; // Can't use this inside the function
	input.onchange = function() {
		self.wertGeaendert();
	};
};

de_gedoplan_demos_vaadin_demo_ui_components_JsHtml5TextField = function() {

	var element = this.getElement();
	var textField = new components.JsHtml5TextField(element);

	this.onStateChange = function() {

		textField.setInputType(this.getState().inputType);

	};

	var self = this;
	textField.wertGeaendert = function() {
		self.wertGeandert(textField.getValue());
	};

};