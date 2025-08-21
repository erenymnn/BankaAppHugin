package org.example.utils;

import org.example.controller.BankController;
import org.example.model.Accounts;
import org.example.model.Transactions;
import org.example.model.Users;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class Xml {

    public static void createXML(BankController controller, int userId) {

        if (userId == -1) {
            System.out.println("Lutfen Giris Yapiniz");
            return;
        }

        try {
            // creat XML Document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Root Element
            Element rootElement = doc.createElement("BankaAppHugin");
            doc.appendChild(rootElement);

            // information user
            Users user = controller.getUserById(userId);
            Element userElement = doc.createElement("User");
            userElement.setAttribute("id", String.valueOf(user.getId()));
            userElement.appendChild(doc.createElement("username")).appendChild(doc.createTextNode(user.getUsername()));
            rootElement.appendChild(userElement);

            //information Account
            Accounts account = controller.getAccount(userId);
            Element accountElement = doc.createElement("Account");
            accountElement.setAttribute("id", String.valueOf(account.getId()));
            accountElement.appendChild(doc.createElement("Balance")).appendChild(doc.createTextNode(String.valueOf(account.getBalance())));
            rootElement.appendChild(accountElement);

            //transaction history
            Element transactionsElement = doc.createElement("Transactions");
            for (Transactions t : controller.getTransactions(userId)) {
                Element tElem = doc.createElement("Transaction");
                tElem.setAttribute("id", String.valueOf(t.getAccount_id()));
                tElem.appendChild(doc.createElement("Type")).appendChild(doc.createTextNode(String.valueOf(t.getType())));
                tElem.appendChild(doc.createElement("Amount")).appendChild(doc.createTextNode(String.valueOf(t.getAmount())));
                tElem.appendChild(doc.createElement("CreatedAt")).appendChild(doc.createTextNode(String.valueOf(t.getCreatedAt())));
                transactionsElement.appendChild(tElem);
            }
            rootElement.appendChild(transactionsElement);

            //Creat Xml folder(if don't folder)

            File xmlFolder = new File("XML");
            if (!xmlFolder.exists()) {
                xmlFolder.mkdir();
            }

            //write XML File to folder
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFolder, "BankaAppHugin" + userId + ".xml"));
            transformer.transform(source, result);

            System.out.println("XML Dosyasi basariyla olusturuldu: " + "XML/BankaAppHugin" + userId + ".xml");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }


    }


}






