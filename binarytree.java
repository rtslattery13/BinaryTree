/* RYAN SLATTERY
** BINARY TREE CONSTRUCTION AND TRAVERSAL (JAVA)
** MAY 11, 2023
** PURPOSE: SIMILAR TO THE C++ BINARY TREE PROGRAM, THIS PROGRAM WAS DEVELOPED TO HELP ME REFRESH
** MY JAVA PROGRAMMING SKILLS. THIS PROGRAM CONTAINS SOME DIFFERENCES, GIVEN THAT EVERYTHING IN
** JAVA MUST BE IN A CLASS, ALL OF THE MEMBER FUNCTIONS MUST BE STATIC (GIVEN THAT I DO NOT WANT
** TO CREATE AN INSTANCE OF THE BINARY TREE CLASS), AND ALL FUNCTIONS HAPPEN TO BE CALL BY VALUE 
** (UNLIKE C++ WHERE I COULD USE CALL BY REFERENCE TO MODIFY STRUCTURES).
** NOTES:
** 1. DO NOT ENTER DUPLICATE VALUES!!! I DID NOT SET BOUNDARIES TO HANDLE THAT. THIS IS MEANT TO 
** TAKE A "SET" OF NUMBERS (NO DUPLICATES). YOU WILL END UP IN AN INFINITE LOOP IF YOU DO.
** 2. -1 IS USED TO END THE LOOP. THIS VALUE WILL NOT BE INCLUDED IN THE TREE.
*/

// IMPORTS
import java.util.Scanner;

// NODE CLASS
// CONTAINS value (int), next (Node), prev (Node). CLASS CONSTRUCTOR INITIALIZES THE VALUE TO SOME
// INPUT NUMBER, BUT THE NEXT AND PREV NODES ARE LEFT AS NULL.
class Node {
	int value;
	Node next;
	Node prev;

	Node(int input_num) {
		this.value = input_num;
	}

}

// BINARY TREE CLASS (PRIMARY CLASS)
// ALL TRAVERSAL METHODS ARE INCLUDED IN THE CLASS AS "STATIC" FUNCTIONS, SO WE DO NOT HAVE TO
// CREATE A BINARY TREE OBJECT TO ACCESS THEM.
public class binarytree {

	/* INORDER TRAVERSE METHOD
	** THIS METHOD TRAVERSES THE BINARY TREE IN INCREASING ORDER. THE EXACT ALGORITHM IS:
	** 1. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER VALUES OF A SUBTREE)
	** 2. PRINT THE CURRENT NODE IN EACH RECURSIVE METHOD (THIS WILL MEAN THE LAST RECURSIVE METHOD
	** FROM THE PREVIOUS NODES WILL PRINT FIRST)
	** 3. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
	** INPUT: Node head (head node of the tree [basically the root])
	** OUTPUT: none (void function)
	*/
	static void inorder_traverse(Node head) {
		if (head == null) {
			return;
		}

		inorder_traverse(head.prev);
		System.out.print(head.value + " ");
		inorder_traverse(head.next);
	}

	/* PREORDER TRAVERSE METHOD
	** THIS METHOD TRAVERSES THE BINARY TREE FROM THE ROOT NODE FIRST, AND THEN VISITS THE LEAF NODES.
	** ODDLY ENOUGH, THE PREORDER TRAVERSAL WILL PROVIDE A LIST OF THE TREE ELEMENTS THAT, IF INPUTTED
	** AGAIN, WILL PRODUCE AN EXACT COPY OF THE BINARY TREE! THE EXACT ALGORITHM IS:
	** 1. PRINT THE CURRENT NODE (ROOT IS THE FIRST TO PRINT, AND LEAF NODES AFTER)
	** 2. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER NODES OF A SUBTREE)
	** 3. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
	** INPUT: Node head (head node of the tree [basically the root])
	** OUTPUT: none (void function)
	*/
	static void preorder_traverse(Node head) {
		if (head == null) {
			return;
		}

		System.out.print(head.value + " ");
		preorder_traverse(head.prev);
		preorder_traverse(head.next);
	}

	/* POSTORDER TRAVERSE METHOD
	** THIS METHOD TRAVERSES THE BINARY TREE FROM THE BOTTOM NODES OF EACH SUBTREE FIRST, AND THEN 
	** VISITS THE ROOT NODE LAST. THE EXACT ALGORITHM IS:
	** 1. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER NODES OF A SUBTREE)
	** 2. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
	** 3. PRINT THE CURRENT NODE IN EACH RECURSIVE METHOD (THIS MEANS AFTER RECURSION, THE BOTTOM 
	** LEAVES OF EACH SUBTREE WILL PRINT FIRST AND THE ROOT NODE WILL BE THE LAST)
	** INPUT: Node head (head node of the tree [basically the root])
	** OUTPUT: none (void function)
	*/
	static void postorder_traverse(Node head) {
		if (head == null) {
			return;
		}

		postorder_traverse(head.prev);
		postorder_traverse(head.next);
		System.out.print(head.value + " ");
	}

	// MAIN METHOD
	public static void main(String[] args) {
		// CREATING THE INPUT SCANNER AND GENERATING A USER INPUT PROMPT
		Scanner input = new Scanner(System.in);
		System.out.print("ENTER THE FIRST NUMBER: ");
		int user_input = input.nextInt();

		// CREATING THE ROOT NODE; INITIALIZING IT WITH THE NUMBER OBTAINED FROM THE USER. CREATING
		// A "CURRENT" NODE WHICH WILL HELP SORT THROUGH THE TREE.
		Node root = new Node(user_input);
		Node current = root;

		// DO-WHILE LOOP FOR USER INPUTS
		do {
			System.out.print("ENTER THE NEXT NUMBER (OR ENTER -1 TO END): ");
			user_input = input.nextInt();
			
			// CREATING A "PARENT" NODE AS A PLACEHOLDER. THIS WILL BE USED WITH THE "CURRENT" NODE
			Node parent = new Node(0);

			// BREAKING THE LOOP IF THE USER ENTERS -1
			if (user_input == -1) {
				break;
			}

			// A WHILE LOOP THAT TESTS WHEN THE "CURRENT" NODE IS NULL. IF IT IS NULL, WE KNOW THAT
			// WE CAN ATTACH A LEAF TO A "PARENT" NODE ON THE BINARY TREE. THE "PARENT" NODE WILL
			// ALWAYS BE THE NODE ABOVE THE GIVEN "CURRENT" NODE.
			// NOTE: I COULD NOT MAKE A FUNCTION TO ADD A LEAF DUE TO JAVA FUNCTIONS ONLY BEING CALL 
			// BY VALUE.
			while (current != null) {
				if (user_input < current.value) {
					parent = current;
					current = current.prev;
				} else if (user_input > current.value) {
					parent = current;
					current = current.next;
				}
			}

			// CREATING THE NEW LEAF NODE
			Node leaf = new Node(user_input);

			// ADDING THE NEW LEAF NODE TO THE PARENT NODE.
			if (user_input < parent.value) {
				parent.prev = leaf;
			} else if (user_input > parent.value) {
				parent.next = leaf;
			}

			// RESET CURRENT TO THE ROOT NODE FOR THE NEXT INPUT
			current = root;

		} while (user_input != -1);

		// OUTPUTTING THE THREE DIFFERENT TRAVERSALS OF THE BINARY TREE
		System.out.print("INORDER TRAVERSE OF TREE: ");
		inorder_traverse(root);

		System.out.print('\n' + "PREORDER TRAVERSE OF TREE: ");
		preorder_traverse(root);

		System.out.print('\n' + "POSTORDER TRAVERSE OF TREE: ");
		postorder_traverse(root);
	}
}