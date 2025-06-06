/* Generated by AN DISI Unibo */ 
package it.unibo.gamebuilder

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023
//Sept2024
import org.slf4j.Logger
import org.slf4j.LoggerFactory 
import org.json.simple.parser.JSONParser
import org.json.simple.JSONObject


//User imports JAN2024
import unibo.basicomm23.mqtt.*

class Gamebuilder ( name: String, scope: CoroutineScope, isconfined: Boolean=false, isdynamic: Boolean=false ) : 
          ActorBasicFsm( name, scope, confined=isconfined, dynamically=isdynamic ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		//IF actor.withobj !== null val actor.withobj.name» = actor.withobj.method»ENDIF
		 
		 	var RowsN = 0
		 	var ColsN = 0
		 
		    var NAllCells      =  0  //set in s0
		    var NCellsCreated  =  0  //set in handlecellecreated 		
		  	
		 	var LastI = 0
			var LastJ = 0
			
			
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						 
								val res = conway.GridSupport25.readGridConfig("gridConfig.json")
								RowsN     = res.get(0)
								ColsN     = res.get(1)
								NAllCells = RowsN * ColsN    
						CommUtils.outblack("$name | START !!!!!!!!!!!!! ")
						CommUtils.outblue("$name | RowsN=$RowsN ColsN=$ColsN")
						 conway.GridSupport25.createPlayers( myself,RowsN,ColsN )  
						delay(2000) 
						CommUtils.outmagenta("$name | players created ")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="handleAddtogane",cond=whenRequest("addtogame"))
				}	 
				state("handleAddtogane") { //this:State
					action { //it:State
						if(  LastJ == ColsN  
						 ){if(  LastI == RowsN  
						 ){ val CelName = "rejected_0_0"  
						answer("addtogame", "addedtogame", "addedtogame($CelName,0,0)"   )  
						}
						else
						 { LastI++; LastJ = 0  
						  val CelName = "cell_${LastI}_$LastJ"  
						 answer("addtogame", "addedtogame", "addedtogame($CelName,$RowsN,$ColsN)"   )  
						   LastJ++  
						 }
						}
						else
						 { val CelName = "cell_${LastI}_$LastJ"  
						 answer("addtogame", "addedtogame", "addedtogame($CelName,$RowsN,$ColsN)"   )  
						   LastJ++  
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t02",targetState="handleAddtogane",cond=whenRequest("addtogame"))
					transition(edgeName="t03",targetState="handleCellcreated",cond=whenDispatch("cellcreated"))
				}	 
				state("handleCellcreated") { //this:State
					action { //it:State
						 NCellsCreated++  
						if(  NCellsCreated ==  NAllCells  
						 ){CommUtils.outmagenta("$name | ALL CELL $NAllCells CREATED !!!! ")
						delay(1000) 
						forward("allcellready", "allcellready(ok)" ,name ) 
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t04",targetState="handleAddtogane",cond=whenRequest("addtogame"))
					transition(edgeName="t05",targetState="handleCellcreated",cond=whenDispatch("cellcreated"))
					transition(edgeName="t06",targetState="activateGamemaster",cond=whenDispatch("allcellready"))
				}	 
				state("activateGamemaster") { //this:State
					action { //it:State
						forward("activateMaster", "activateMaster($NAllCells)" ,"gamemaster" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
