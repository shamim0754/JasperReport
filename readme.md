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
   
   <!-- a section of element content that is marked for the parser to interpret as only character data, not markup. -->
   <field name = "country" class = "java.lang.String">
      <fieldDescription><![CDATA[country]]></fieldDescription>
   </field>
   
   <field name = "name" class = "java.lang.String">
      <fieldDescription><![CDATA[name]]></fieldDescription>
   </field>
   <pageHeader>
		<band height="70" splitType="Stretch">
			<![CDATA[Report]]>
		</band>
	</pageHeader>	
   <columnHeader>
      <band height = "23">
         
         <staticText>
            <reportElement mode = "Opaque" x = "0" y = "3" 
               width = "535" height = "15" backcolor = "#70A9A9" />
            
            <box>
               <bottomPen lineWidth = "1.0" lineColor = "#CCCCCC" />
            </box>
            
            <textElement />
            <text><![CDATA[]]> </text>
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
               <![CDATA[$F{country}]]>
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