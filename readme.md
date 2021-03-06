### JasperReports ###
The JasperReports Library is the world's most popular open source reporting engine. It is entirely written in Java and it is able to use data coming from any kind of data source and produce pixel-perfect documents that can be viewed, printed or exported in a variety of document formats including HTML, PDF, Excel, OpenOffice and Word.

### How to start ###
1. create java maven project <br/>
mvn archetype:generate -DgroupId=com.javaaround.TestApp -DartifactId=TestApp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
2. Add dependency at pom.xml
```xml
 <dependency>
      <groupId>net.sf.jasperreports</groupId>
      <artifactId>jasperreports</artifactId>
      <version>6.3.0</version>
    </dependency>
```
add `exec-maven-plugin` for execute app when maven package
```xml
<plugin>
          <groupId>org.codehaus.mojo</groupId>
          <artifactId>exec-maven-plugin</artifactId>
          <version>1.4.0</version>
          <executions>
            <execution>
              <id>my-execution</id>
              <phase>package</phase>
              <goals>
                <goal>java</goal>
              </goals>
            </execution>
          </executions>
          <configuration>
            <mainClass>com.javaaround.TestApp.App</mainClass>
            <!--<classpathScope>main</classpathScope> defualt-->
             <arguments>  
               <argument>arg0</argument>  
               <argument>arg1</argument>  
             </arguments>  
          </configuration>
        </plugin>
```
3.create report template file(template.jrxml)
```xml
<?xml version = "1.0" encoding = "UTF-8"?>
<!DOCTYPE jasperReport PUBLIC "//JasperReports//DTD Report Design//EN"
   "http://jasperreports.sourceforge.net/dtds/jasperreport.dtd">

<jasperReport xmlns = "http://jasperreports.sourceforge.net/jasperreports"
   xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation = "http://jasperreports.sourceforge.net/jasperreports
   http://jasperreports.sourceforge.net/xsd/jasperreport.xsd"
   name = "template" language = "java" pageWidth = "595"
   pageHeight = "842" columnWidth = "555" leftMargin = "20" rightMargin = "20"
   topMargin = "20" bottomMargin = "20">
   
   <!-- a section of element content that is marked for the parser to 
   <br>interpret as only character data, not markup. -->
   <field name = "country" class = "java.lang.String">
      <fieldDescription><![CDATA[address]]></fieldDescription>
   </field>
   
   <field name = "name" class = "java.lang.String">
      <fieldDescription><![CDATA[name]]></fieldDescription>
   </field>	
   <columnHeader>
      <band height = "23">
         <staticText>
            <reportElement mode = "Opaque" x = "0" y = "3" 
               width = "535" height = "15" backcolor = "#70A9A9" />
            <box>
               <bottomPen lineWidth = "1.0" lineColor = "#CCCCCC" />
            </box>
            <textElement />
            <text><![CDATA[SL]]> </text>
         </staticText>
         
         <staticText>
            <reportElement x = "414" y = "3" width = "121" height = "15" />
            <textElement textAlignment = "Center" verticalAlignment = "Middle">
               <font isBold = "true" />
            </textElement>
            <text><![CDATA[Country]]></text>
         </staticText>
         
         <staticText>
            <reportElement x = "0" y = "3" width = "136" height = "15" />
            <textElement textAlignment = "Center" verticalAlignment = "Middle">
               <font isBold = "true" />
            </textElement>
            <text><![CDATA[Name]]></text>
         </staticText>
      </band>
   </columnHeader>
   
   <detail>
      <band height = "16">
         <staticText>
            <reportElement mode = "Opaque" x = "0" y = "0" 
               width = "535" height = "14" backcolor = "#E5ECF9" />
            <box>
               <bottomPen lineWidth = "0.25" lineColor = "#CCCCCC" />
            </box>
            <textElement />
            <text><![CDATA[]]> </text>
         </staticText>
         
         <textField>
            <reportElement x = "414" y = "0" width = "121" height = "15" />
            <textElement textAlignment = "Center" verticalAlignment = "Middle">
               <font size = "9" />
            </textElement>
            <textFieldExpression class = "java.lang.String">
               <![CDATA[$F{address}]]>
            </textFieldExpression>
         </textField>
         
         <textField>
            <reportElement x = "0" y = "0" width = "136" height = "15" />
            <textElement textAlignment = "Center" verticalAlignment = "Middle" />
            <textFieldExpression class = "java.lang.String">
               <![CDATA[$F{name}]]>
            </textFieldExpression>
         </textField>
      </band>
   </detail>
</jasperReport>
```  
4.create App.java
```java
package com.javaaround.TestApp;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperDesignViewer;

public class App {
	public static void main(String[] args) throws JRException {
		
		 String sourceFileName = "F:/newsoft/workspace/TestApp/src/main/java/com/"+
		 "javaaround/TestApp/template.jrxml";       
		 JasperDesignViewer jasperDesignViewer =
		 new JasperDesignViewer(sourceFileName, true); // true means it not xml file 
		 jasperDesignViewer.setVisible(true); 
		
		
	}

}

``` 
5.Run app by following command
`mvn clean package` 
![Image of Yaktocat](image/2.png)

### Report Main Section ###
```xml
<title></title> <!-- only once at the beginning of the report-->

<pageheader></pageheader> <!-- each page in the generated document-->

<columnheader></columnheader> <!-- define column -->

<groupheader></groupheader> 

<detail></detail> <!-- each line of data supplied by the report's data source -->

<groupfooter></groupfooter>

<columnfooter></columnfooter>

<pagefooter></pagefooter> <!-- the bottom of each page -->

<lastpagefooter></lastpagefooter>

<summary></summary> <!-- only once at the end of the report -->

<nodata></nodata> <!-- produce the report output if no source data-->

<background></background> <!-- creating page watermarks -->
```
### Report Element ###
1. `<field name>` : This element is used to map data from data sources or queries
2. `<fieldDescription>` : This element maps the field name with the appropriate element in the XML file
3. `<staticText>` : contains static text
4. `<textField>` : define text field data
5. `<textFieldExpression>` : define variable expression
6. `$F{country}` : variable expression that map `<field name>`
7. `<band>` : Bands contain the data, which is displayed in the report.

### Report With static Data ###
create employee.java
```java
package com.javaaround.TestApp.model;

public class Employee {
	private String name;
	private String address;
	private Double salary;
	
	public Employee(String name, String address,Double salary) {
		super();
		this.name = name;
		this.address = address;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
}

```

update App.java
```java
package com.javaaround.TestApp;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.javaaround.TestApp.model.Employee;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperDesignViewer;
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

		Map parameters = new HashMap();
		try {
			//fill data
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
			JasperViewer.viewReport(jasperPrint, false);
		} catch (Exception e) {

		}
		
	}

}

```

update template.jrxml for auto serial number
```xml
	<!-- create variable for auto serial number -->
	<variable name = "countNumber" class = "java.lang.Integer" calculation = "Count">
      <variableExpression>
         <![CDATA[Boolean.TRUE]]>
      </variableExpression>
   </variable>
   <!-- display variable -->
   <textField>
	    <reportElement mode = "Opaque" x = "0" y = "0" 
	       width = "535" height = "14" backcolor = "#E5ECF9" />
	    
	    <box>
	       <bottomPen lineWidth = "0.25" lineColor = "#CCCCCC" />
	    </box>
	    
	    <textElement />
	    <textFieldExpression class = "java.lang.String">
	       <![CDATA[$V{countNumber}]]>
	    </textFieldExpression>
 	</textField>
```

Run app again by following command
`mvn clean package` 

![Image of Yaktocat](image/1.png)

### Recommnedation ###
It is recommended to use precompile version(.jasper) into production environment instead every time compile report form .jrxml
following code snipet use for load report from .jasper instead of .jrxml

```java
String sourceFileName = "F:/newsoft/workspace/TestApp/src/main/java/com/javaaround/TestApp/";
 //compile report first
        //JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);
  
  //generate template.jasper
  JasperCompileManager.compileReportToFile(
        		sourceFileName+"template.jrxml",
        		sourceFileName+"template.jasper");
  //use compile version for jasper report			
  JasperReport jasperReport = (JasperReport)JRLoader.loadObjectFromFile(sourceFileName+"template.jasper");
```

### Exporting Reports(pdf,xls) ###
update App.java
```java
package com.javaaround.TestApp;

import java.io.File;
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

public class App {
	public static void main(String[] args) throws JRException {

		JasperPrint jasperPrint = null;

		String sourceFileName = "F:/newsoft/workspace/TestApp/src/main/java/com/"+
		"javaaround/TestApp/template.jrxml";
		/*JasperDesignViewer jasperDesignViewer = new JasperDesignViewer(sourceFileName, true); // true
		jasperDesignViewer.setVisible(true);*/
		
		//compile report first
		JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);
		
		//data
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee("Shamim", "Tangail"));
		employeeList.add(new Employee("Alamin", "Rajbari"));
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(employeeList);

		Map parameters = new HashMap();
		try {
			//fill data
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
			//JasperViewer.viewReport(jasperPrint, false);
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
		} catch (Exception e) {

		}
		
	}

}

```

### Parameter pass to template ###
add param at App.java
```java
Map parameters = new HashMap();
// Passing ReportTitle and Author as parameters
parameters.put("ReportTitle", "List of Employee");
parameters.put("Author", "Prepared By Shamim Miah");
```

update template.jrxml
```xml
<parameter name = "ReportTitle" class = "java.lang.String"/>
<parameter name = "Author" class = "java.lang.String"/>
<title>
      <band height = "70">
         
         <line>
            <reportElement x = "0" y = "0" width = "515" height = "1"/>
         </line>
         
         <textField isBlankWhenNull = "true" bookmarkLevel = "1">
            <reportElement x = "0" y = "10" width = "515" height = "30"/>
           
            <textElement textAlignment = "Center">
               <font size = "22"/>
            </textElement>
            
            <textFieldExpression class = "java.lang.String">
               <![CDATA[$P{ReportTitle}]]>
            </textFieldExpression>
				
            <anchorNameExpression>
               <![CDATA["Title"]]>
            </anchorNameExpression>
         </textField>
         
         <textField isBlankWhenNull = "true">
            <reportElement  x = "0" y = "40" width = "515" height = "20"/>
            
            <textElement textAlignment = "Center">
               <font size = "10"/>
            </textElement>
            
            <textFieldExpression class = "java.lang.String">
               <![CDATA[$P{Author}]]>
            </textFieldExpression>
         </textField>
      
      </band>
   </title>
```

Run app again by following command
`mvn clean package` 

![Image of Yaktocat](image/3.png)

### Data from database ###
update App.java
```java
Class.forName("com.mysql.jdbc.Driver");
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
//fill data
//jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
```

update template.jrxml
```xml
<queryString language="SQL">
		<![CDATA[SELECT name,address FROM employee2;]]>
</queryString>
```

### Sorting ###
update template.jrxml
```
<sortField name = "name" order = "Descending"/>
```
![Image of Yaktocat](image/6.png)

### Expression Revised ###
| description        | format           | usage   |
| -----------------  |:---------------: | ------: |
| Field Reference    |$F{and}           | $F{Name}|
| Variable Reference |$V{and}           | $V{Name}|
| Parameter Reference|$P{and}           | $P{Name}|
| Resource Message   |$R{key}           | $R{report.title}|
| ifelse   |<![CDATA[$F{country}.isEmpty() ? "NO COUNTRY" : $F{country}]]>|

### variables  ###
Variables can perform various calculations based on the corresponding expressions values such as count, sum, average, lowest, highest, variance, etc.

declaration
```xml
<variable name = "CityNumber" class = "java.lang.Integer" incrementType = "Group"
   incrementGroup = "CityGroup" calculation = "Count">
   <variableExpression>
      <![CDATA[Boolean.TRUE]]>
   </variableExpression>
</variable>
```
__name__ == name of the variable <br>
__Calculation__ = following value supported<br>
Average,Count,First,Highest,Sum,System (custom formula using scriplets)<br>
 
__IncrementType__ = following value supported<br>
__Column__ − The variable value is recalculated at the end of each column.<br>
__Group__ − The variable value is recalculated when the group specified by incrementGroup changes.<br>
__None__ − The variable value is recalculated with every record.<br>
__Page__ − The variable value is recalculated at the end of every page.<br>
__Report__ − The variable value is recalculated once, at the end of the report.<br>

__IncrementGroup__ == name of the group at which the variable value is recalculated, when incrementType is Group<br>
__ResetType__==determines when the value of a variable is reset<br>
__Column__ − The variable value is reset at the beginning of each column.<br>
__Group__ − The variable value is reset when the group specified by incrementGroup changes.<br>
__None__ − The variable value is never reset.<br>
__Page__ − The variable value is reset at the beginning of every page.<br>
__Report__ − The variable value is reset only once, at the beginning of the report.<br>
__ResetGroup__==name of the group at which the variable value is reset, when resetType is Group

### Sum of Total/Average ###
update template.jrxml
```xml
<variable name="total" class="java.lang.Double" calculation="Sum">
    <variableExpression><![CDATA[$F{salary}]]></variableExpression>
</variable>
<variable name="average" class="java.lang.Double" calculation="Average">
    <variableExpression><![CDATA[$F{salary}]]></variableExpression>
</variable>
   
<summary>
    <band height="20">
        <staticText>
            <reportElement x="0" y="0" width="100" height="20"/>
            <box leftPadding="10"/>
            <textElement>
                <font isBold="true"/>
            </textElement>
            <text><![CDATA[Total]]></text>
        </staticText>
        <textField>
            <reportElement x="100" y="0" width="100" height="20"/>
            <box leftPadding="10"/>
            <textElement>
                <font isBold="true" isItalic="true"/>
            </textElement>
            <textFieldExpression><![CDATA[$V{total}]]></textFieldExpression>
        </textField>
   
        <staticText>
            <reportElement x="160" y="0" width="100" height="20"/>
            <box leftPadding="10"/>
            <textElement>
                <font isBold="true"/>
            </textElement>
            <text><![CDATA[Average]]></text>
        </staticText>
        <textField>
            <reportElement x="250" y="0" width="100" height="20"/>
            <box leftPadding="10"/>
            <textElement>
                <font isBold="true" isItalic="true"/>
            </textElement>
            <textFieldExpression><![CDATA[$V{average}]]></textFieldExpression>
        </textField>
   </band>
</summary>	
```

![Image of Yaktocat](image/4.png)
### Group Data ###
 A report group represents a sequence of consecutive records in the data source, which have something in common, such as the value of a certain report fields.
During the iteration through the data source at report-filling time if the value of the group expression changes, a group rupture occurs and the corresponding <groupFooter> and <groupHeader> sections are inserted in the resulting document.

update template.jrxml
```xml
   <variable name = "AddressNumber" class = "java.lang.Integer"
      incrementType = "Group" incrementGroup = "AddressGroup"
      calculation = "Count">
      <variableExpression><![CDATA[Boolean.TRUE]]></variableExpression>
   </variable>
   
   <group name = "AddressGroup" minHeightToStartNewPage = "60">
      <groupExpression><![CDATA[$F{address}]]></groupExpression>
      
      <groupHeader>
         <band height = "20">
            
            <textField evaluationTime = "Group" evaluationGroup = "AddressGroup"
               bookmarkLevel = "1">
               <reportElement mode = "Opaque" x = "0" y = "5" width = "515"
                  height = "15" backcolor = "#C0C0C0"/>
               
               <box leftPadding = "10">
                  <bottomPen lineWidth = "1.0"/>
               </box>
               <textElement/>
               
               <textFieldExpression class = "java.lang.String">
                  <![CDATA["  " + String.valueOf($V{AddressNumber}) + ". "
                  + String.valueOf($F{address})]]>
               </textFieldExpression>
               
               <anchorNameExpression>
                  <![CDATA[String.valueOf($F{address})]]>
               </anchorNameExpression>
            </textField>
         
         </band>
      </groupHeader>
      
      <groupFooter>
         <band height = "20">
            
            <staticText>
               <reportElement x = "400" y = "1" width = "60" height = "15"/>
               <textElement textAlignment = "Right"/>
               <text><![CDATA[Count :]]></text>
            </staticText>
            
            <textField>
               <reportElement x = "460" y = "1" width = "30" height = "15"/>
               <textElement textAlignment = "Right"/>
               
               <textFieldExpression class = "java.lang.Integer">
                  <![CDATA[$V{AddressGroup_COUNT}]]>
               </textFieldExpression>
            </textField>
         
         </band>
      </groupFooter>
   
   </group>
```

![Image of Yaktocat](image/5.png)

### Scriptlets. ###
There are situations when a complex functionality such as String manipulations, building of Maps, or Lists of objects in memory or manipulations of dates using 3rd party Java APIs cannot be achieved easily using report expressions or variables.For such situations, JasperReports Scriptlets to use pure java code based on events fire.
### Scriptlets Event ###
```java
package com.javaaround.TestApp;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class MyScriptlet extends JRAbstractScriptlet  {
	@Override
	public void afterReportInit() throws JRScriptletException {
		
	}

	@Override
	public void afterColumnInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterDetailEval() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGroupInit(String arg0) throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPageInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeColumnInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeDetailEval() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGroupInit(String arg0) throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePageInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeReportInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}
}

```

JRDefaultScriptlet have empty implementation of JRAbstractScriptlet

create MyScriptlet.java
```java
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

```

update template.xml
```xml
<jasperReport xmlns = "http://jasperreports.sourceforge.net/jasperreports"
   xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation = "http://jasperreports.sourceforge.net/jasperreports
   http://jasperreports.sourceforge.net/xsd/jasperreport.xsd"
   name = "template" language = "java" pageWidth = "595"
   pageHeight = "842" columnWidth = "555" leftMargin = "20" rightMargin = "20"
   topMargin = "20" bottomMargin = "20" scriptletClass = "com.javaaround.TestApp.MyScriptlet">
 <variable name="someVar" class="java.lang.String" calculation="System"/>
 <textField isBlankWhenNull = "true">
    <reportElement  x = "0" y = "10" width = "600" height = "30"/>
    <textElement textAlignment = "Left">
       <font size = "10"/>
    </textElement>
    <textFieldExpression class = "java.lang.String">
       <![CDATA[$V{someVar}]]>
    </textFieldExpression>
		
 </textField>
 <textField isStretchWithOverflow = "true">
    <reportElement positionType = "Float" x = "200" y = "0" width = "515"
       height = "15" />
       
    <textElement textAlignment = "Center">
       <font size = "10"/>
    </textElement>
       
    <textFieldExpression class = "java.lang.String">
       <![CDATA[$P{REPORT_SCRIPTLET}.hello()]]>
    </textFieldExpression>
    
</textField>           
```

![Image of Yaktocat](image/7.png)

### Subreports ###
[guide](https://www.tutorialspoint.com/jasper_reports/jasper_create_subreports.htm)
### Chart  ###
[guide](https://www.tutorialspoint.com/jasper_reports/jasper_creating_charts.htm)
### internationalization ###
[guide](https://www.tutorialspoint.com/jasper_reports/jasper_internationalization.htm)

### Jasper Report Design(GUI) ###

1. [iReport]
(http://ireport-tutorial.blogspot.com/) support upto jdk 1.7.  iReport development has already terminated.beter to use Jasper studio
2. Jasper Studio
(http://ireport-tutorial.blogspot.com/)
http://www.trainingwithliveproject.com/p/java-tutorials.html
### Jasper Report Api ###

Jasper Report Api simplifies report creation easy and hides complexity of jasper report
such api

1. [DynamicJasper](http://dynamicjasper.com/)
2. [DynamicReport](http://www.dynamicreports.org/)

### dynamicreports-vs-dynamicjasper ###

[guide](http://stackoverflow.com/questions/4956841/dynamicreports-vs-dynamicjasper)

### How to start with iReport ###
iReport is a visual tool to obtain XML files for JasperReports

[download : ](https://sourceforge.net/projects/ireport/files/iReport/iReport-5.6.0/)

### How to start with DynamicJasper ###

add dependency at pom.xml
```xml
<dependency>
	<groupId>ar.com.fdvs</groupId>
	<artifactId>DynamicJasper</artifactId>
	<version>5.0.9</version>
</dependency>
```
create App.java
```java
package com.javaaround.TestApp;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.javaaround.TestApp.model.Employee;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
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

		FastReportBuilder drb = new FastReportBuilder();
		DynamicReport dr;
		try {
			dr = drb
					//,display column name, object field,type,width,true(FixedWidth)
					//.addColumn("Sl", "SL", String.class.getName(),30)
					.addColumn("Name", "name", String.class.getName(),30)
					.addColumn("Address", "address", String.class.getName(),50)
					.addColumn("Salary", "salary", Double.class.getName(),50)
					
					.setTitle("Employee List")
					.setSubtitle("This report was generated at " + LocalDate.now())
					.setPrintBackgroundOnOddRows(true)
					.setUseFullPageWidth(true)
					.build();
			//data
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(new Employee("Shamim", "Tangail",50000.00));
			employeeList.add(new Employee("Alamin", "Rajbari",10000.00));
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(employeeList);
			
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr,
			 new ClassicLayoutManager(), beanColDataSource);
			JasperViewer.viewReport(jp);
			
		} catch (ColumnBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}

```

![Image of Yaktocat](image/8.png)

 
### more control on the report option ###
 
if you want to more control(e.g custom field such as auto serial number) on the report option you need to use `DynamicReportBuilder` instead of `FastReportBuilder`

update App.java
```java
package com.javaaround.TestApp;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.javaaround.TestApp.model.Employee;

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
		
		DynamicReport dr;
		try {
			dr = drb.build();
			//data
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(new Employee("Shamim", "Tangail",50000.00));
			employeeList.add(new Employee("Alamin", "Rajbari",10000.00));
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(employeeList);
			
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(
			dr, new ClassicLayoutManager(), beanColDataSource);
			JasperViewer.viewReport(jp);
			
		} catch (ColumnBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

``` 

![Image of Yaktocat](image/9.png)

### Data from database with sql where ###

update App.java
```java
//sql
drb.setQuery("select * from employee2 where address = $P{city}", DJConstants.QUERY_LANGUAGE_SQL);

Map params = new HashMap();
params.put("city", "Dhaka");

Class.forName("com.mysql.jdbc.Driver");
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

```

![Image of Yaktocat](image/10.png)

### Adding Groups ###

update App.java
```java
//create group label
DJGroupLabel subTotalLabel = new DJGroupLabel("SubTotal",null);
//create column group
GroupBuilder gb = new GroupBuilder();
DJGroup groupAddress = gb.setCriteriaColumn((PropertyColumn) columnAddress)
		.addFooterVariable(columnSalary,DJCalculation.SUM)
		.setGroupLayout(GroupLayout.DEFAULT)
		.setFooterHeight(new Integer(0))
		.setFooterLabel(subTotalLabel)
		.build();
//add group
drb.addGroup(groupAddress); 
//sql
drb.setQuery("select * from employee2", DJConstants.QUERY_LANGUAGE_SQL);				
```

![Image of Yaktocat](image/11.png)

[complete documentation](http://dynamicjasper.com/documentation-examples/how-to-2/)








