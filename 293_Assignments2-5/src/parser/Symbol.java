package parser;
import java.util.*;

interface Symbol {
	//Parses the input into a node, possibly leaving a remainder.
	//True if parsing was successful, false otherwise.
	ParseState parse(List<Token> input);
}
