# Craftsmanship2-5
Assignments #2-5 (cumulative) for EECS 293, Software Craftsmanship.


Unzip .java files and compile like any other java program.

====================Changelist=======================

Everywhere:
HW2-Some spacing fixes
HW2-Removed TODO statements
HW3-NullPointerExceptions replaced with Objects.requireNonNull()

Cache:
HW2-Now checks null
HW2-Now uses computeIfAbsent()

Variable:
HW2-Variable TerminalSymbol no longer stored for every instance

Connector:
HW2-Removed unneccessary SecurityException
HW2-Complexity of LegalConnectorType() reduced from 5 to 2
HW2-Complexity of toString() reduced using a Table, added in TerminalSymbol

Node:
HW2-Removed toString(), it is a java method

LeafNode:
HW2-Fixed error message in build(), previously used wrong method name
HW2-toList() simplified to one line

InternalNode:
HW2-toList() now uses global variable childList, to avoid calculating more than once
HW2-toList() now uses addAll()
HW2-Removed comment "//else"
HW2-Class fields declared at top, were previously above methods they were used in
HW2-Fixed error message in build(), previously used wrong method name

TerminalSymbol:
HW3-parse() logic reworked, removed foreach loop and used 'this' instead of TerminalSymbol.getType()

SymbolSequence:
HW3-Changed final variables from uppercase to lowercase (PRODUCTION -> production)
HW3-Made exception messages unique to see which build() method was the problem
HW3-symbol.parse(remainder) turned into local variable "current"

NonTerminalSymbol:
HW3-Map is no longer anonymous class
HW3-Some static block cleanup
HW3-Altered parse() method logic
HW3-Removed repeated code in ParseInput()

=================================================