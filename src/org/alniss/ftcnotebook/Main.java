package org.alniss.ftcnotebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
	    //MessageWrangler messageWrangler = new MessageWrangler();
	    //messageWrangler.init();

		/*Structure.Notebook notebook = new Structure.Notebook();
		Structure.Day day = new Structure.Day("09-01");
		Structure.Section section = new Structure.Section("Testing Section");
		Structure.Entry entry = new Structure.Entry();

		entry.addMessage("a");
		entry.addMessage("b");
		entry.addMessage("c");
		section.addEntry(entry);
		day.addSection(section);
		notebook.addDay(day);

		String testMessage = notebook
				.getDay("09-01")
				.getSection("Testing Section")
				.getEntry(0)
				.getMessageID(1);

		System.out.println(testMessage);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		System.out.println(gson.toJson(notebook));*/

		MessageWrangler wrangler = new MessageWrangler();
		wrangler.init();
		Collection<Message> messages = wrangler.getMessages();

		Structure.Notebook notebook = new Structure.Notebook();

		for (Message message : messages) {
			try {
				String date = getDate(message);
				Structure.Day day = notebook.getDay(date);
				if (day == null) {
					day = new Structure.Day(date);
					notebook.addDay(day);
				}
				if (day.getSections().size() == 0)
					day.addSection(new Structure.Section("Test Section"));
				Structure.Entry entry = new Structure.Entry();
				entry.addMessage(message.getMessageID());
				day.getSection("Test Section").addEntry(entry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		System.out.println(gson.toJson(notebook));
    }

	private static String getDate(Message message) {
    	String messageString =  message.getCurrentBase();
    	return messageString.substring(messageString.indexOf("[") + 1, messageString.indexOf("]"));
	}
}
