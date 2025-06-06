/* Generated by AN DISI Unibo */ 
package it.unibo.gamecontroller

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
import main.java.*

class Gamecontroller ( name: String, scope: CoroutineScope, isconfined: Boolean=false, isdynamic: Boolean=false ) : 
          ActorBasicFsm( name, scope, confined=isconfined, dynamically=isdynamic ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		//IF actor.withobj !== null val actor.withobj.name» = actor.withobj.method»ENDIF
		 var Running = false 
		   lateinit var inoutdev : OutInForGui
		   val guiinterpreter = main.java.GuiCmdTranslator( myself )  //ADDED
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outblack("$name | START !!!!!!!!!!!!! ")
						 inoutdev = OutInForGui(myself)  
						delay(1000) 
						observeResource("localhost","8360","ctxcells","gamemaster","gridEmpty")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("work") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t07",targetState="startTheGame",cond=whenDispatch("startcmd"))
					transition(edgeName="t08",targetState="work",cond=whenDispatch("stopcmd"))
					transition(edgeName="t09",targetState="doclearTheGame",cond=whenDispatch("clearcmd"))
					interrupthandle(edgeName="t010",targetState="handleGuiMsg",cond=whenEvent("kernel_rawmsg"),interruptedStateTransitions)
				}	 
				state("handleGuiMsg") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("kernel_rawmsg(ARG)"), Term.createTerm("kernel_rawmsg(ARG)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 guiinterpreter.cvtToApplMessage( payloadArg(0) )  
						}
						returnFromInterrupt(interruptedStateTransitions)
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
				state("startTheGame") { //this:State
					action { //it:State
						forward("start", "start(init)" ,"gamemaster" ) 
						 Running = true  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="gameStarted", cond=doswitch() )
				}	 
				state("gameStarted") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t011",targetState="gameStarted",cond=whenDispatch("startcmd"))
					transition(edgeName="t012",targetState="gameStarted",cond=whenDispatch("clearcmd"))
					transition(edgeName="t013",targetState="stopTheGame",cond=whenDispatch("stopcmd"))
					transition(edgeName="t014",targetState="work",cond=whenDispatch("gridEmpty"))
					interrupthandle(edgeName="t015",targetState="handleGuiMsg",cond=whenEvent("kernel_rawmsg"),interruptedStateTransitions)
				}	 
				state("stopTheGame") { //this:State
					action { //it:State
						forward("stop", "stop(ok)" ,"gamemaster" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="gameStopped", cond=doswitch() )
				}	 
				state("gameStopped") { //this:State
					action { //it:State
						 Running = false  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t016",targetState="doclearTheGame",cond=whenDispatch("clearcmd"))
					transition(edgeName="t017",targetState="startTheGame",cond=whenDispatch("startcmd"))
					transition(edgeName="t018",targetState="gameStopped",cond=whenDispatch("stopcmd"))
					interrupthandle(edgeName="t019",targetState="handleGuiMsg",cond=whenEvent("kernel_rawmsg"),interruptedStateTransitions)
				}	 
				state("doclearTheGame") { //this:State
					action { //it:State
						CommUtils.outblack("$name | emit clearCell")
						emit("clearCell", "clearCell(ok)" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
			}
		}
} 
