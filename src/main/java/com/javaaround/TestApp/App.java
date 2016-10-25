package com.javaaround.TestApp;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.javaaround.TestApp.model.Employee;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class App {
	public static void main(String[] args) throws JRException {

		JasperPrint jasperPrint = null;

		String sourceFileName = "F:/newsoft/workspace/TestApp/src/main/java/com/javaaround/TestApp/template.jrxml";
		/*JasperDesignViewer jasperDesignViewer = new JasperDesignViewer(sourceFileName, true); // true
		jasperDesignViewer.setVisible(true);*/
		
		//compile report first
		JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);
		
		//data
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee("Shamim", "Tangail"));
		employeeList.add(new Employee("Alamin", "Rajbari"));
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(employeeList);
		
		//
		
		Map parameters = new HashMap();
		// Passing ReportTitle and Author as parameters
        parameters.put("ReportTitle", "List of Employee");
        parameters.put("Author", "Prepared By Shamim Miah");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			//fill data
			//jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			
			if (jasperPrint != null) {
	            //export pdf
	            JasperExportManager.exportReportToPdfFile(jasperPrint,
	               "F://sample_report.pdf");

	            //export html
	            JasperExportManager.exportReportToHtmlFile(jasperPrint,
	               "F://sample_report.html");

	            //export excel
	            JRXlsxExporter exporter = new JRXlsxExporter();
	            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	            File outputFile = new File("F://sample_report.xlsx");
	            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
	            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
	            configuration.setDetectCellType(true);//Set configuration as you like it!!
	            configuration.setCollapseRowSpan(false);
	            
	            exporter.setConfiguration(configuration);
	            
	            //export report
	            exporter.exportReport();
	         }
			JasperViewer.viewReport(jasperPrint, false);
			
		} catch (Exception e) {

		}
		/*
		 * // You can use JasperPrint to create PDF
		 * //JasperFillManager.printReportToPdfFile(jasperPrint,
		 * "BasicReport.pdf"); } catch (JRException e) { e.printStackTrace(); }
		 * DJ test = new DJ(); test.testReport(); test.exportToJRXML();
		 * JasperViewer.viewReport(test.jp); //finally display the report report
		 * JasperDesignViewer.viewReportDesign(test.jr);
		 * 
		 * 
		 * out.println("Hllo");
		 * 
		 * /* //JasperReportBuilder report = DynamicReports.report(); Connection
		 * connection = null; try { Class.forName("com.mysql.jdbc.Driver");
		 * connection =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root"
		 * ,""); } catch (SQLException e) { e.printStackTrace(); return; } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); return; }
		 * 
		 * JasperReportBuilder report = DynamicReports.report();//a new report
		 * report .columns( Columns.column("Customer Id", "id",
		 * DataTypes.integerType()), Columns.column("First Name", "first_name",
		 * DataTypes.stringType()), Columns.column("Last Name", "last_name",
		 * DataTypes.stringType()), Columns.column("Date", "date",
		 * DataTypes.dateType())) .title( Components.text("SimpleReportExample")
		 * .setHorizontalAlignment(HorizontalAlignment.CENTER))
		 * .pageFooter(Components.pageXofY()) .setDataSource(
		 * "SELECT id, first_name, last_name, date FROM customers", connection);
		 * 
		 * try { //show the report report.show();
		 * 
		 * //export the report to a pdf file report.toPdf(new
		 * FileOutputStream("e:/report.pdf")); } catch (DRException e) {
		 * e.printStackTrace(); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); }
		 * 
		 */
	}

}
