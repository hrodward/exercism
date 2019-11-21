class Markdown {

	private static final String MARKUP_ITALICS = "<em>$1</em>";
	private static final String SYMBOL_ITALICS = "_(.+)_";
	private static final String MARKUP_STRONG = "<strong>$1</strong>";
	private static final String SYMBOL_STRONG = "__(.+)__";

	String parse(final String markdown) {

		String[] lines = markdown.split("\n");

		StringBuilder result = new StringBuilder(); // use StringBuilder instead of String

		boolean activeList = false;

		for (String line : lines) { // transform loop to for-each

			String theLine = parseHeader(line);

			if (theLine == null) {
				theLine = parseListItem(line);
			}

			if (theLine == null) {
				theLine = parseParagraph(line);
			}

			if (theLine.startsWith("<li>") && !activeList) { // replace matches for startsWith; delete unnecessary conditions
				activeList = true;
				result.append("<ul>").append(theLine);
			} else if (!theLine.startsWith("<li>") && activeList) { // replace matches for startsWith
				activeList = false;
				result.append("</ul>").append(theLine);
			} else {
				result.append(theLine);
			}
		}

		if (activeList) {
			result.append("</ul>");
		}

		return result.toString();
	}

	private String parseHeader(final String markdown) {
		// replace loop with startsWith + indexOf
		if(!markdown.startsWith("#")) {
			return null;
		}
		int count = markdown.indexOf(" ");
		return surroundWith(markdown.substring(count + 1), "h" + count); // autobox variable + surroundWith
	}

	private String parseListItem(final String markdown) {
		if (markdown.startsWith("*")) {
			String skipAsterisk = markdown.substring(2);
			String listItemString = parseSomeSymbols(skipAsterisk);
			return surroundWith(listItemString, "li"); // surroundWith
		}

		return null;
	}

	private String parseParagraph(final String markdown) {
		return surroundWith(parseSomeSymbols(markdown), "p"); // use generic surroundwith function
	}

	private String surroundWith(final String markdown, final String tag) {
		return String.format("<%s>%s</%s>", tag, markdown, tag);
	}

	private String parseSomeSymbols(final String markdown) {
		return markItalics(markStrong(markdown)); // functions should have just 1 purpose
	}

	private String markStrong(final String markdown) {
		return markdown.replaceAll(SYMBOL_STRONG, MARKUP_STRONG);
	}

	private String markItalics(final String markdown) {
		return markdown.replaceAll(SYMBOL_ITALICS, MARKUP_ITALICS);
	}

}