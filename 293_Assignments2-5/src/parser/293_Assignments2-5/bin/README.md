# Craftsmanship2-5
Assignments #2-5 (cumulative) for EECS 293, Software Craftsmanship.


Unzip .java files and compile like any other java program.  
====================Changelist=======================

Everywhere:  
HW4-Some spacing fixes  

Cache:   
HW4-ComputeIfAbsent() uses Objects.requireNonNull to take up less space  

Connector:  
HW4-TerminalSymbol.values() now includes VARIABLE condition  
HW4-SymbolSequence.EPSILON now less redundant, uses build()  

InternalNode:  
HW4-replaced -> with :: in stream  

LeafNode:  
HW4-removed toList() re-initialization, now only uses asList()  

Builder:  
HW4-simplify() now checks that first child is not null   

SymbolSequence:  
HW4-deleted simplify() call in match() so that Builder handles that instead  

NonTerminalSymbol:  
HW4-Implemented second simplify piece of productions  
HW4-Added missing mappings*  

=================================================