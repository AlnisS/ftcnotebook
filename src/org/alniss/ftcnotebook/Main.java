package org.alniss.ftcnotebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
	    //MessageWrangler messageWrangler = new MessageWrangler();
	    //messageWrangler.init();

		Structure.Notebook notebook = new Structure.Notebook();
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

		System.out.println(gson.toJson(notebook));
    }
}
