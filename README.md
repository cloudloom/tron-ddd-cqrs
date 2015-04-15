# tron-ddd-cqrs
The aim is to build a framework for DDD, CQRS, Event Sourcing and some associated utilities on top of Spring. 

Features
 - DDD
  - Base classes for Aggregate root, Entity and Value objects. 
  - @DomainMethod annotation: Used to annotate Aggregate Root domain methods. 
  - Domain method advisor: Advisor for domnain method annotation. 
  - @PersistChanges annotation: User to annotate domain service methods, which will mark methods to flush changes. 
  - Persist changes advisor: Advisor for persist changes annotation. 
 - Assemblers
  - Assembler resolver to scan class path and find if an assebler is implemented to convert Entity to Resource of vice versa. 
  - If an Assembler is not implemented, create one at runtime. 
 - Request mapping over riding 
