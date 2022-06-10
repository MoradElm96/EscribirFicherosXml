/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escribirXmlConDome;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Morad
 */
public class EscribirXml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TransformerException {
        try {
            // TODO code application logic here

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();

            //importar con org
            Document documento = implementation.createDocument(null, "concesionario", null);

            documento.setXmlVersion("1.0");

            //sacar la razi  fuera del for
            //este for crea 3 veces los nodos
            for (int i = 1; i <= 3; i++) {
                //etiquetas y importar con org

                Element coche = documento.createElement("coche");
                Element coches = documento.createElement("coches");

                //atributo
                Attr id = documento.createAttribute("id");
                id.setValue(String.valueOf(i));
                coche.setAttributeNode(id);

                Element matricula = documento.createElement("matricula");
                Text textMatricula = documento.createTextNode("2020ABC");
                matricula.appendChild(textMatricula);
                coche.appendChild(matricula);

                Element marca = documento.createElement("marca");
                Text textMarca = documento.createTextNode("Audi");
                marca.appendChild(textMarca);
                coche.appendChild(marca);

                Element precio = documento.createElement("precio");
                Text textPrecio = documento.createTextNode("2000");
                precio.appendChild(textPrecio);
                coche.appendChild(precio);

                coches.appendChild(coche);

                documento.getDocumentElement().appendChild(coches);

            }

            //creamos el fichero, javax
            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File("concesionario.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(EscribirXml.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
