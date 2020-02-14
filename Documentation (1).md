# Documentation 

### Packages (3) 

 ## 1. Person 
**Attributes (7)**
```
+ personId(long)
+ firstName (String) 
+ lastName (String)
+ fullName (String)
+ password (String)
+ email (String)
+ quiz (Assessment)  
```
**Methods (10)**
```
+ getFirstName () <-----> + setFirstName () 
+ getLastName () <-----> + setLastName ()
+ getFullName () <------> + (No setter needed)
+ getPassword () <------> + setPassword ()
+ getEmail () <-----------> + setEmail ()
```
## 2. Assessment

**Attributes (3)**
```
+ assessmentId (long)
+ numberOfQuestions (int) const
+ questions [numberOfquestions] (Question) //array of Question objects 
```
**Methods (2)**
```
+ getFullSummary () *returns the full question array []*
+ getQuestionSummary (int) *returns a Question object from the question array []*
```
## 3. Question

**Attributes (5)**
```
+ questionId (long)
+ numberOfResponseOptions (int) const
+ question (String)
+ responseOptions [numberOfResponseOptions] (String)
+ response (int) 0 à numberOfResponseOprions - 1
```
**Methods (3)**
```
+ getQuestion () <----> setQuestion (String)
+ getResponseOptions () <----> setResponseOptions (&args: String, String[])
+ getResponse () <-----> setResponse (int)
```
**[We will keep adding more stuff on here as we go ...** **😊]**

> Written with [StackEdit](https://stackedit.io/).
