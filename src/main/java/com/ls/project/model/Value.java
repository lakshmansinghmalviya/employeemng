package com.ls.project.model;

import java.util.List;

public class Value {
private String messaging_product;
private Metadata metadata;
private List<Contact> contacts;
private List<Message> messages;
@Override
public String toString() {
	return "Value [messaging_product=" + messaging_product + ", metadata=" + metadata + ", contacts=" + contacts
			+ ", messages=" + messages + "]";
}
public String getMessaging_product() {
	return messaging_product;
}
public void setMessaging_product(String messaging_product) {
	this.messaging_product = messaging_product;
}
public Metadata getMetadata() {
	return metadata;
}
public void setMetadata(Metadata metadata) {
	this.metadata = metadata;
}
public List<Contact> getContacts() {
	return contacts;
}
public void setContacts(List<Contact> contacts) {
	this.contacts = contacts;
}
public List<Message> getMessages() {
	return messages;
}
public void setMessages(List<Message> messages) {
	this.messages = messages;
}
}
