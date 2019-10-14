# Terminology [term]

This document defines terminology used by the Gac14 Specification. 

## Gac14 Specification [term.spec]

The Gac14 Specification, the specification, this specification, or Gac14 Specification all refer to the collection of documents at <https://chorman0773.github.io/Gac14-Datapack/> which define the Gac14 Specification.

## Customization Point [term.custom]

A Customization Point is a part of the specification which can be changed by consumers of this specification. These customization points are intended to provide some sort of control over high level constructs defined by this specification. 
Most customization points are set by a datapack or the Gac14 Datapack, though some may not be.

## Configuration Point [term.config]

A Configuration Point is a Customization Point which it is Implementation-Defined how the customization is achieved. 

## Requirement Levels [term.req]

At various places in this specification, the terms MUST, MUST NOT, SHALL, SHALL NOT, MAY, SHOULD, SHOULD NOT, REQUIRED, RECOMMENDED, NOT RECOMMENDED, and OPTIONAL may be used to indicate requirements. These terms, in all capitals, are used as defined by [[RFC 2119]](https://tools.ietf.org/html/rfc2119) and have the same meaning. 

MUST, MUST NOT, SHOULD, and SHOULD NOT, are imperative. When used, they define a contract which is REQUIRED or RECOMMENDED (depending on the specific requirement) to be fufilled by the implementation. 

SHALL, SHALL NOT, and MAY are declarative. When used, they define a construct which is REQUIRED or OPTIONAL to be provided by the implementation. 

REQUIRED, RECOMMENED, NOT RECOMMENED, and OPTIONAL are informative. When used, they define a concept which is to be observed by the implementation. 

## Wording [term.wording]

In additional to requirements, the wording of this specification can also indicate what is being defined. Three types of language are used to define parts of this specification, "imperative", "declarative", and "informative". 
Other types of language are not used to define parts of this specification, rather to provide examples, add a note to implementors, or convey an idea, but these have no specific bearing on the specification. 
Additionally, informative language may be used in sections without defining a part of the specification, as above, when indicated (such as with Ex., note, or when the language is contained within parenthesis). Neither declarative nor imperative language will be used in this manner. 


Imperative language defines a contract between the Gac14 Specification and the Gac14 Datapack. Depending on the specifics, it may be REQUIRED or RECOMMENED to be fufilled. In all cases where the explicit requirements SHOULD or SHOULD NOT are not used, the contract is REQUIRED to be fufilled, as though the explicit requirements MUST or MUST NOT are used. 


Declarative language defines a construct of the Gac14 Specification and/or the Gac14 Datapack. These constructs may be REQUIRED, RECOMMENDED, or OPTIONAL to be provided depending on the specifics (almost never will they be REQUIRED when simply provided with declarative language, REQUIRED constructs are usually defined in conjunction with a contract of semantics). 
Constructs may have optional or required semantics or be bound to a concept which is optional or required. 

Informative language defines a concept of the specification. These concepts can be REQUIRED, RECOMMEND, or OPTIONAL to be observed. Concepts may be bound to optional or required constructs or contracts. 

## Undefined Behavior [term.ub]

Undefined Behavior occurs when various terms of this specification are broken. This may apply to both the datapack and the specification. 
Such occurences will be described with "The behavior of ... is undefined" or "The behavior is undefined", or "undefined behavior". 

If undefined behavior occurs in the datapack, then the semantic structure of the datapack is no longer required to be observed, either by the datapack or the implementation. At the point which undefined behavior occurs, all concepts cease to have any defined meaning and are no longer bound by any rules, explicit or implicit, provided by this specification. 

If undefined behavior occurs in the specification, then the defined behavior of the implementation is no longer required to be fufilled. 
At the point which undefined behavior occurs, all contracts cease to have any requirements, and all concepts cease to be required to be observed. 
At such a point, the implementation ceases to be bound any any rules, structural or semantic, explicit or implied, which are defined by this specification. 

In particular, datapacks and implementations are allowed to make assumptions about constructs and contracts, based on what does not result in undefined behavior. 
(The results of causing undefined behavior may therefore be as intended, and may have functional semantics. On the other hand, the results may be destructive, non-functional, or semantically broken) 

Additionally, datapacks and implementations are within there right to give meaning to undefined behavior, either causing an error, or giving a semantically correct result. However this cannot be relied upon in any case. 
Conditionally-supported will be used over undefined behavior in contexts where it is intended that implementations or datapacks may give meaning to otherwise undefined behavior. 

## Unspecified Behavior [term.unspecified]

Unspecified Behavior is behavior in which the implementation or datapack is provided a choice which is potentially bounded or restricted, of the semantic behavior of a contract, construct, or concept. 
The implementation or datapack MUST choose between these choices of semantic behavior but the choice MAY be made in any manner and is not required to be consistent. 

## Implemenation-Defined Behavior [term.impldef]

Implementation-Defined Behavior is Unspecified Behavior where the implementation or datapack is REQUIRED to document how the choice is made.

## Conditionally-supported Behavior [term.conditional]

Conditionally-supported Behavior is behavior which results from a situation which could possibly be invalid. 
Whether or not the behavior is supported is Implementation-Defined. 
The results of either situation are defined or implied by the context (if the behavior is not supported, the result is usually undefined behavior).

## Core Gameplay Concept [term.core]

A Core Gameplay Concept is a concept not defined by this specification. 
Such a Concept may be taken from the Larger Minecraft Community. 
Defintions may be provided in this specification for informative purposes. 

## PvP [term.pvp]

PvP, Player v Player, Player vs Player, or Player versus Player, refers to a core gameplay concept which has one or more members of the server engaged in active combat with one or more other members. 

## PvE [term.pve]

PvE, Player v Environment, Player vs Environment, or Player versus Environment, refers to a core gameplay concept which has one or more members of the server engaged in active combat with an in game entity, or where one or more players are actively attempting to survive normal gameplay. 

## Command [term.cmd]

A Command is a core gameplay concept which is initiated by one or more members of the server, or by the server, 
and results in some well-defined action to occur on the server. 

