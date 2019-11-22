import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Markdown {

	private static final String MARKUP_ITALICS = "<em>$1</em>";
	private static final String SYMBOL_ITALICS = "_(.+)_";
	private static final String MARKUP_STRONG = "<strong>$1</strong>";
	private static final String SYMBOL_STRONG = "__(.+)__";

	final AtomicBoolean activeList = new AtomicBoolean(false);

	String parse(final String markdown) {

		String html = Stream.of(markdown.split("\n"))
			.map(line -> {
				String parsedLine = parseLine(line);
				return openOrCloseList(parsedLine);
			})
			.collect(Collectors.joining());

		return activeList.get() ? html.concat("</ul>") : html;
	}

	private String parseLine(final String line) {
		String parsedLine = null;
		switch (line.charAt(0)) {
			case '#':
				parsedLine = parseHeader(line);
				break;
			case '*':
				parsedLine = parseListItem(line);
				break;
			default:
				parsedLine = parseParagraph(line);
		}
		return parsedLine;
	}

	private String openOrCloseList(String parsedLine) {
		// replace matches for startsWith; delete unnecessary conditions
		boolean isBeginningOfList = parsedLine.startsWith("<li>") && !activeList.get();
		if (isBeginningOfList) {
			activeList.set(true);
			parsedLine = "<ul>" + parsedLine;
		} else {
			boolean listHasEnded = !parsedLine.startsWith("<li>") && activeList.get(); // replace matches for startsWith
			if (listHasEnded) {
				activeList.set(false);
				parsedLine = "</ul>" + parsedLine;
			}
		} // delete unnecessary else
		return parsedLine;
	}

	private String parseHeader(final String markdown) {
		// replace loop with indexOf
		int count = markdown.indexOf(" ");
		String skipPoundSigns = markdown.substring(count + 1);
		return surroundWith(skipPoundSigns, "h" + count); // autobox variable + surroundWith
	}

	private String parseListItem(final String markdown) {
		String skipAsterisk = markdown.substring(markdown.indexOf(" ") + 1); // replace hardcoded '2'
		String listItem = parseSomeSymbols(skipAsterisk);
		return surroundWith(listItem, "li"); // surroundWith
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