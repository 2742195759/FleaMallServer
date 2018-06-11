![类图](https://g.gravizo.com/svg?
@startuml ; 
class Message { ; 
	-data {some data which can be used in handle%28%29};
	-- Abstract Function --;
	{abstract}+void sendAndReturn%28%29 ;
	{abstract}+Respond handle%28%29 ; 
	{abstract}+boolean isOwner%28%29 ; 
	{abstract}+void getAuthorityArray%28%29;
}; 
note top : "This class is a abstract class for all the Message to be send .\nevery subclass extends Message and override the abstract class .\nTo implement your class with handle function . ";
class Respond { ;
	-String state;
	-String extra;
	-- Access Getter and Setter function --;
};
note top : "This class is a respond which is sent by the server to the client\nthe origin data is the must filled scope which indicate the\nstate of this msg\
nif the state not equals success , \n means the failed of the message\n";
Message <|--- MsgAccountXXX ; 
Message <|--- MsgCommodityXXX ; 
Message <|--- MsgUserXXX;
Message -- Respond ; 
class RspSingleRow {;
	-HashMap data;
	-- ;
	+String getString%28String key%29;
	+Int getInt%28String key%29 ;
	other data access function ; 
} ; 
note bottom : "This class is used to \nconvert the database resultset to the client.\n Means a row , it's inner \nconstruct is a hashmap.\n";
class RspMultiRow {;
	-LinkedArray%3CRspSingleRow%3E rows;
	+get%28int index%29;
};
note bottom : "This class is used to convert \nmultirow result of the database. \nIt is consisted of multi \nRspSingleRow and the access function.";
Respond <|--- RspSingleRow;
Respond <|--- RspMultiRow ;
Respond <|--- RspImage ; 
RspMultiRow *-- RspSingleRow;  
class Authority ; 
note top : "Authority class is used to ecapesulate many function \nwhich is used by authority check.\n the check is before the handle \n function.";
Authority .. Message ; 
@enduml;
)



![各个功能类组成图](https://g.gravizo.com/svg?
@startuml ; 
left to right direction ; 
package UserManager { ;
	class MsgAccountDelete ; 
	class MsgAccountInfo ; 
	class MsgAccountUpdate ; 
	class MsgRegister ; 
	note right: Register a centain type account \ndefault is student ; 
} ; 
package CommodityManager { ; 
	class MsgCommodityByTime ;
	note right: Get the commodity sorted \nby the publish time;
	class MsgCommodityByTable ;
	note right: Get the commodity in Selling , Loving , \n or Requiring table which belong to specific \n user ;
	class MsgCommodityByCno ; 
	note right: Get the spectific commodity \nwhich no is given cno ; 
	class MsgCommodityCreateSell ; 
	note right: Create a new commodity in selling table ;
	class MsgCommodityOff ; 
	note right: off the commodity \n means delete the shelf ;
	class MsgCommodityOwner ;
	note right: Get the owner of the commodity ;
	class MsgImageFetch ; 
	class MsgImageSave ; 
	class MsgLoveOperate ; 
	note right: Control the love table\ninclude create,delete,update,search\nsearch can be get in MsgCommodityByTable ;
};
package Notify { ; 
	class MsgNodifyGet ; 
	note right: Get the specific user's notification .;
};
UserManager -[hidden]----> CommodityManager ; 
@enduml ; 
)

