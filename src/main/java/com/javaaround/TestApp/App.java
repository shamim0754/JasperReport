package com.javaaround.TestApp;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperDesignViewer;

public class App {
	public static void main(String[] args) throws JRException {
		
		  JasperPrint jasperPrint = null; 
		  
		 String sourceFileName = "/TestApp/src/main/java/com/javaaround/TestApp/report.jrxml";    
		 JasperDesignViewer jasperDesignViewer =
		  new JasperDesignViewer(sourceFileName, true); // true means it not xml file 
		  jasperDesignViewer.setVisible(true); 
		 /*JasperReport jasperReport =
		 * JasperCompileManager.compileReport(sourceFileName);
		 * 
		 * ArrayList<DataBean> dataList = new ArrayList<DataBean>();
		 * dataList.add(new DataBean ("Manisha", "India")); dataList.add(new
		 * DataBean("Dennis Ritchie", "USA")); dataList.add(new
		 * DataBean("V.Anand", "India")); dataList.add(new DataBean("Shrinath",
		 * "California"));
		 * 
		 * JRBeanCollectionDataSource beanColDataSource = new
		 * JRBeanCollectionDataSource(dataList); Map parameters = new HashMap();
		 * try { jasperPrint = JasperFillManager.fillReport( jasperReport,
		 * parameters, beanColDataSource);
		 * 
		 * // JasperViewer.viewReport(jasperPrint,false);
		 * 
		 * // You can use JasperPrint to create PDF
		 * //JasperFillManager.printReportToPdfFile(jasperPrint,
		 * "BasicReport.pdf"); } catch (JRException e) { e.printStackTrace(); }
		 * /*DJ test = new DJ(); test.testReport(); test.exportToJRXML();
		 * JasperViewer.viewReport(test.jp); //finally display the report report
		 * JasperDesignViewer.viewReportDesign(test.jr);
		 * 
		 
		out.println("Hllo");

		/*
		 * //JasperReportBuilder report = DynamicReports.report(); Connection
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
		 */
		
	}

}
