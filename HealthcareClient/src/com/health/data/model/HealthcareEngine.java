package com.health.data.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HealthcareEngine {

    private ArrayList<Account> accounts;
    private ArrayList<Topic> topics;

    public HealthcareEngine() {
        accounts = new ArrayList<>();
        topics = getTopics();
        accounts = getAccounts();
        System.out.println(accounts.size()); 
    }

    //-------------------------ACCOUNT PROPERTIES-----------------------------//
    private ArrayList<Account> getAccounts() {
        //ArrayList<Account> accounts = new ArrayList<>();
        try {
            // fake end point that returns XML response
            String URL = "http://localhost:43424/HealthcareProject/api/account";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);

            // normalize XML response
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("account");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Account account = new Account(
                            Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent()),
                            elem.getElementsByTagName("username").item(0).getTextContent(),
                            (elem.getElementsByTagName("password").item(0).getTextContent())
                    );
                    accounts.add(account);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return accounts;
    }

    public Account createAccount(String username, String passowrd) {
        int id = getNextAccountId();
        Account account = new Account(id, username, passowrd);
        Runtime runtime = Runtime.getRuntime();
        String content = getAccountXML(account);
        System.out.println(id);
        System.out.println("");
        System.out.println(content);
        try {
            Process process = runtime.exec("curl -X POST http://localhost:43424/HealthcareServer/api/account/create -H \"Content-Type: application/xml\" -H \"Accept: application/xml\" -d \"" + content + "\"");
            int resultCode = process.waitFor();
            if (resultCode == 0) {
                System.out.println("Inside Create Account");
            }
        } catch (Throwable cause) {
            System.out.println(cause);
            return null;
        }
        //updateAccountList();
        accounts = getAccounts();
        return account;
    }

    //TODO [2]
    public Account deleteAccount(int id) {
        Account account = searchAccount(id);
        if (account != null) {
            Runtime runtime = Runtime.getRuntime();
            try {
                Process process = runtime.exec("curl -X \"DELETE\" http://localhost:43424/HealthcareServer/api/account/delete/" + id);
                int resultCode = process.waitFor();
                if (resultCode == 0) {
                    System.out.println("Inside Create Account");
                }
            } catch (Throwable cause) {
                System.out.println(cause);
                return null;
            }
            //updateAccountList();
            accounts = getAccounts();
        }
        return account;
    }

    //TODO [3]
    public Account searchAccount(int id) {
        System.out.println("sdf");
        for (int i = 0; i < accounts.size(); i++) {
            if (id == accounts.get(i).getId()) {
                return accounts.get(i);
            }
        }
        return null;
    }

    public Account searchAccount(String username) {
        for (int i = 0; i < accounts.size(); i++) {
            if (username.equals(accounts.get(i).getUsername())) {
                return accounts.get(i);
            }
        }
        return null;
    }

    //TODO [4]
    public Account updateAccount(int id, String username, String password) {
        Account account = searchAccount(id);
        if (account != null) {
            account.setUsername(username);
            account.setPassword(password);
            Runtime runtime = Runtime.getRuntime();
            String content = getAccountXML(account);
            System.out.println(id);
            System.out.println("");
            System.out.println(content);
            try {
                Process process = runtime.exec("curl -X PUT http://localhost:43424/HealthcareServer/api/account/update -H \"Content-Type: application/xml\" -H \"Accept: application/xml\" -d \"" + content + "\"");
                int resultCode = process.waitFor();
                if (resultCode == 0) {
                    System.out.println("Inside Create Account");
                }
            } catch (Throwable cause) {
                System.out.println(cause);
                return null;
            }
        }
        //updateAccountList();
        accounts = getAccounts();
        return account;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    private int getNextAccountId() {
        return accounts.size() + 1;
    }

    private String getAccountXML(Account account) {
        String XML = null;
        try {
            // create an instance of `JAXBContext`
            JAXBContext context = JAXBContext.newInstance(Account.class);

            // create an instance of `Marshaller`
            Marshaller marshaller = context.createMarshaller();

            // enable pretty-print XML output
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // write XML to `StringWriter`
            StringWriter sw = new StringWriter();

            // convert book object to XML
            marshaller.marshal(account, sw);

            // print the XML
            XML = sw.toString();

            XML = XML.replaceAll(" ", "");
            XML = XML.replaceAll("\n", "");
            XML = XML.replace("<?xmlversion=\"1.0\"encoding=\"UTF-8\"standalone=\"yes\"?>", "");
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return XML;
    }
    
    private void updateAccountList(){
        accounts = getAccounts();
    }

    //-------------------------TOPIC PROPERTIES-------------------------------//
    //TODO [5]
    public ArrayList<Topic> getTopics() {
       
        ArrayList<Topic> topics = new ArrayList<>();
        try { 
            // fake end point that returns XML response
            String URL = "http://localhost:43424/HealthcareProject/api/topic";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);

            // normalize XML response
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("topic");
            for (int i = 0; i < nodeList.getLength(); i++) {
                
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Topic topic = new Topic(
                            Integer.parseInt(elem.getElementsByTagName("id").item(1).getTextContent()),
                            elem.getElementsByTagName("title").item(0).getTextContent(),
                            (elem.getElementsByTagName("body").item(0).getTextContent()),
                            new Account(Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent()),elem.getElementsByTagName("username").item(0).getTextContent(),
                            (elem.getElementsByTagName("password").item(0).getTextContent()))
                    );
                    topics.add(topic);
                    System.out.println(topic.getBody());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return topics;
    }

    //TODO [6]
    public Topic createTopic(String title, String body, Account author) throws FileNotFoundException {
        int id = getNextTopicId();
        Topic topic = new Topic(id, title, body, author);
        Runtime runtime = Runtime.getRuntime();
        String content = getTopicXML(topic);
        content = content.replaceAll("author", "authorId");
        System.out.println(id);
        System.out.println(content);
        File file = new File("XML.txt");
        PrintWriter write = new PrintWriter(file);
        write.print(content);
        write.flush();
        write.close();
        System.out.println(file.getAbsoluteFile());
        //String uri = "curl -X POST http://localhost:43424/HealthcareServer/api/topic/create -H \"Content-Type: application/xml\" -H \"Accept: application/xml\" -d \"" + content + "\"";
        String uri = "curl -X POST http://localhost:43424/HealthcareServer/api/topic/create -H \"Content-Type: application/xml\" -H \"Accept: application/xml\" -d @" + file.getAbsolutePath();
        //System.out.println(uri);
        try {
            Process process = runtime.exec(uri);
            int resultCode = process.waitFor();
            if (resultCode == 0) {
                System.out.println("Inside Create Account");
            }
        } catch (Throwable cause) {
            System.out.println(cause);
            return null;
        }
        updateTopicList();
        file.delete();
        return topic;
    }

    //TODO [7]
    public Topic deleteTopic(int id) {
        Topic topic = searchTopic(id);
        if (topic != null) {
            Runtime runtime = Runtime.getRuntime();
            try {
                Process process = runtime.exec("curl -X \"DELETE\" http://localhost:43424/HealthcareServer/api/topic/delete/" + id);
                int resultCode = process.waitFor();
                if (resultCode == 0) {
                    System.out.println("Inside Delete Topic");
                }
            } catch (Throwable cause) {
                System.out.println(cause);
                return null;
            }
            updateTopicList();
        }
        return topic;
    }

    //TODO [8]
    public Topic searchTopic(int id) {
        for (int i = 0; i < topics.size(); i++) {
            if (id == topics.get(i).getId()) {
                return topics.get(i);
            }
        }
        return null;
    }
    
    public Topic searchTopic(String title) {
        System.out.println(topics.size());
        for (int i = 0; i < topics.size(); i++) {
            if (title.equals(topics.get(i).getTitle())) {
                
                return topics.get(i);
                
            }
        }
        return null;
    }

    //TODO [9]
    public Topic updateTopic(int id,String title, String body , Account currentAuthor) {
        Topic topic = searchTopic(id);
        if (topic != null) {
            topic.setTitle(title);
            topic.setBody(body);
            topic.setAuthor(currentAuthor);
            Runtime runtime = Runtime.getRuntime();
            String content = getTopicXML(topic);
            System.out.println(id);
            System.out.println("");
            System.out.println(content);
            try {
                Process process = runtime.exec("curl -X PUT http://localhost:43424/HealthcareServer/api/topic/update -H \"Content-Type: application/xml\" -H \"Accept: application/xml\" -d \"" + content + "\"");
                int resultCode = process.waitFor();
                if (resultCode == 0) {
                    System.out.println("Inside Create Topic");
                }
            } catch (Throwable cause) {
                System.out.println(cause);
                return null;
            }
        }
        updateTopicList();
        return topic;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    private int getNextTopicId() {
        return topics.size() + 1;
    }
    
    private String getTopicXML(Topic topic) {
        String XML = null;
        try {
            // create an instance of `JAXBContext`
            JAXBContext context = JAXBContext.newInstance(Topic.class);

            // create an instance of `Marshaller`
            Marshaller marshaller = context.createMarshaller();

            // enable pretty-print XML output
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // write XML to `StringWriter`
            StringWriter sw = new StringWriter();

            // convert book object to XML
            marshaller.marshal(topic, sw);

            // print the XML
            XML = sw.toString();

            //XML = XML.replaceAll(" ", "");
            //XML = XML.replaceAll("\n", "");
            //XML = XML.replace("<?xmlversion=\"1.0\"encoding=\"UTF-8\"standalone=\"yes\"?>", "");
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return XML;
    }
    
    private void updateTopicList(){
        topics = getTopics();
    }

    
}
