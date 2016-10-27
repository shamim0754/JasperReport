package com.javaaround.TestApp;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.javaaround.TestApp.model.Employee;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.chart.NumberExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
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
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitle("Employee List")
			.setSubtitle("This report was generated at " + LocalDate.now());
		
		//create column
		AbstractColumn columnSl = ColumnBuilder.getNew()
				.setCustomExpression(new CustomExpression(){
					public Object evaluate(Map fields, Map variables, Map parameters) {
						 Integer count = (Integer) variables.get("REPORT_COUNT");
						 return count;
					}
					public String getClassName() {
						return Integer.class.getName();
					}
					
				})
				.setTitle("SL")		
				.setWidth(15)
				.build();
		AbstractColumn columnName = ColumnBuilder.getNew()		
		        .setColumnProperty("name", String.class.getName())		
		        .setTitle("Name")		
		        .setWidth(30)		
		        .build();
		AbstractColumn columnAddress = ColumnBuilder.getNew()		
				.setColumnProperty("address", String.class.getName())		
				.setTitle("Address")		
				.setWidth(30)		
				.build();
		
		AbstractColumn columnSalary = ColumnBuilder.getNew()		
				.setColumnProperty("salary", Double.class.getName())		
				.setTitle("Salary")		
				.setWidth(30)		
				.build();
		
		//add column into report
		drb.addColumn(columnSl);
		drb.addColumn(columnName);
		drb.addColumn(columnAddress);
		drb.addColumn(columnSalary);
		//set property
		drb.setUseFullPageWidth(true);
		drb.setPrintBackgroundOnOddRows(true);
		
		//sql
		drb.setQuery("select * from employee2 where address = $P{city}", DJConstants.QUERY_LANGUAGE_SQL);
		
		DynamicReport dr;
		try {
			dr = drb.build();
			//data
			/*ArrayList<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(new Employee("Shamim", "Tangail",50000.00));
			employeeList.add(new Employee("Alamin", "Rajbari",10000.00));
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(employeeList);
			*/
			Map params = new HashMap();
			params.put("city", "Dhaka");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), connection,params);
			JasperViewer.viewReport(jp);
			
		} catch (ColumnBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
