package com.ls.project.model;

import java.util.List;

public class WhatsappDetails2 {

	private String object;
	private List<Entry> entry;

	public static class Entry {
		private String id;
		private List<Change> changes;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public List<Change> getChanges() {
			return changes;
		}

		public void setChanges(List<Change> changes) {
			this.changes = changes;
		}

	}

	public static class Change {
		private String field;
		private Value value;

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			this.value = value;
		}

	}

	public static class Value {
		private String messaging_product;
		private Metadata metadata;
		private List<Contact> contacts;
		private List<Message> messages;

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

	public static class Metadata {
		private String display_phone_number;
		private String phone_number_id;

		public String getDisplay_phone_number() {
			return display_phone_number;
		}

		public void setDisplay_phone_number(String display_phone_number) {
			this.display_phone_number = display_phone_number;
		}

		public String getPhone_number_id() {
			return phone_number_id;
		}

		public void setPhone_number_id(String phone_number_id) {
			this.phone_number_id = phone_number_id;
		}

	}

	public static class Contact {
		private Profile profile;
		private String wa_id;

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public String getWa_id() {
			return wa_id;
		}

		public void setWa_id(String wa_id) {
			this.wa_id = wa_id;
		}

	}

	public static class Profile {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class Message {
		private String from;
		private String id;
		private String timestamp;
		private String type;
		private Context context;
		private Interactive interactive;

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Context getContext() {
			return context;
		}

		public void setContext(Context context) {
			this.context = context;
		}

		public Interactive getInteractive() {
			return interactive;
		}

		public void setInteractive(Interactive interactive) {
			this.interactive = interactive;
		}

	}

	public static class Context {
		private String from;
		private String id;

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

	public static class Interactive {
		private String type;
		private NfmReply nfm_reply;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public NfmReply getNfm_reply() {
			return nfm_reply;
		}

		public void setNfm_reply(NfmReply nfm_reply) {
			this.nfm_reply = nfm_reply;
		}
	}

	public static class NfmReply {
		private String response_json;
		private String body;
		private String name;

		public String getResponse_json() {
			return response_json;
		}

		public void setResponse_json(String response_json) {
			this.response_json = response_json;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

}
