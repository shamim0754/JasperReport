package com.javaaround.TestApp;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class MyScriptlet extends JRDefaultScriptlet {
	@Override
	public void afterReportInit() throws JRScriptletException {
		this.setVariableValue("someVar", "This variable value=" + "was modified by the scriptlet.");
	}

	public String hello() throws JRScriptletException {
		return "Hello! I'm the report's scriptlet object.";
	}
}
