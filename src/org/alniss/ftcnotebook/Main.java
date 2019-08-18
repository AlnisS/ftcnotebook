package org.alniss.ftcnotebook;

public class Main {

    public static void main(String[] args) {
	    System.out.println(DataInfo.slackInputFolder);
	    MessageWrangler messageWrangler = new MessageWrangler();
	    messageWrangler.init();
    }
}
