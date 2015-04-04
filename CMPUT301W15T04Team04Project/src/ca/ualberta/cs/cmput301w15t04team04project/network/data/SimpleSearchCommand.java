package ca.ualberta.cs.cmput301w15t04team04project.network.data;

public class SimpleSearchCommand {
	private String user;
	private String status;
	private String tag;
	public SimpleSearchCommand(String user) {
		this(user, null, null);
	}

	public SimpleSearchCommand(String user, String status) {
		this(user, status, null);
	}

	public SimpleSearchCommand(String user, String status, String tag) {
		this.user = user;
		this.status = status;
		this.tag = tag;
	}
	
	public String getJsonCommand() {
		StringBuffer command = new StringBuffer(
				"{\"query\" : {\"bool\" : {\"must\" :{\"match\" :{\"status\" : \"Progress\"}}}}}   ");
		return command.toString();
	}
	
	/*public String getJsonCommand() {
		StringBuffer command = new StringBuffer(
				"{\"query\" : {\"query_string\" : {\"query\" : \"" + query
						+ "\"");
		if (fields != null) {
			command.append(", \"fields\": [");
			for (int i = 0; i < fields.length; i++) {
				command.append("\"" + fields[i] + "\", ");
			}
			command.delete(command.length() - 2, command.length());
			command.append("]");
		}
		command.append("}}}");
		return command.toString();
	}*/
}
