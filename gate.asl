// Agent Gate in project Intelligent-Deporsitory.mas2j



/* Initial beliefs and rules */



/* Initial goals */



!start.



/* Plans */



+!start : true <- .print("hello world.");
				  .broadcast(tell, pos(gate, 7, 2));
				  .broadcast(tell, go_to_gate);
				  +pos(selector, 8, 5);
				  +pos(selector_full, 3, 5).
				  
+at_gate(A)  <- .print("here is a truck");
				.send(A, tell, at_gate).
				
+empty[source(A)] <- .print("there is a truck"); 
					  !send_to_selector(A). 

+!send_to_selector(A) <- ?pos(selector, X, Y);
						.send(A, tell, pos(selector, X, Y));
						.send(A, tell, go_to_selector).

						
+full[source(A)] <- .print("there is a truck"); 
					!send_to_selector_full(A). 
					
+!send_to_selector_full(A) <- ?pos(selector_full, X, Y);
							.send(A, tell, pos(selector_full, X, Y));
							.send(A, tell, go_to_selector).
