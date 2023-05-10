/* RYAN SLATTERY
** BINARY TREE CONSTRUCTION AND TRAVERSAL
** MAY 09, 2023
** PURPOSE: THIS PROJECT WAS DEVELOPED TO REFRESH SOME OF MY SKILLS ON C++ SYNTAX, DATA 
** STRUCTURES, AND ALGORITHMS. THE USER HAS THE ABILITY TO CONSTRUCT A BINARY TREE OF ANY 
** SIZE WITH EACH INPUT ELEMENT ADDED TO THE TREE IN A "LINKED-LIST" STYLE USING NODE STRUCTS 
** AND POINTERS. THE TREE IS THEN PRINTED USING THE THREE GIVEN TRAVERSAL METHODS: PREORDER,
** INORDER, AND POSTORDER.
** NOTES:
** 1. DO NOT ENTER DUPLICATE VALUES!!! I DID NOT SET BOUNDARIES TO HANDLE THAT IN THE PLACE LEAF
** FUNCTION, AS THIS IS MEANT TO TAKE A "SET" OF NUMBERS (NO DUPLICATES). YOU WILL END UP IN AN 
** INFINITE LOOP IF YOU DO.
** 2. -1 IS USED TO END THE LOOP. THIS VALUE WILL NOT BE INCLUDED IN THE TREE.
*/

// LIBRARIES
#include <iostream>
using namespace std;

// NODE STRUCT
// CONTAINS value (int), prev (node pointer to previous node), next (node pointer to next node)
struct node {
	int value;
	struct node *prev;
	struct node *next;
};

// HEADER METHODS
void inorder_traverse(node* head);
void preorder_traverse(node* head);
void postorder_traverse(node* head);
void place_leaf(node* leaf, node* root);


// MAIN METHOD
int main() {
	// PROMPTING THE USER TO ENTER THE FIRST NUMBER
	int input_num;
	cout << "ENTER THE FIRST NUMBER: ";
	cin >> input_num;

	// GENERATING THE ROOT NODE FOR THE TREE
	node* root = new node();
	root->value = input_num;

	// DO-WHILE LOOP THAT KEEPS TAKING USER INPUTS. -1 WILL END THE LOOP
	do {
		// PROMPTING THE USER TO ENTER THE NEXT GIVEN NUMBERS
		cout << "ENTER THE NEXT NUMBER (OR ENTER -1 TO END): ";
		cin >> input_num;

		// CONDITION OF INPUT: IF INPUT IS -1 THIS IS NOT EXECUTED AND THE LOOP WILL END
		if (input_num != -1) {
			node* item = new node();
			item->value = input_num;

			place_leaf(item, root);
		}
	} while (input_num != -1);

	// OUTPUTTING THE THREE DIFFERENT TRAVERSALS OF THE BINARY TREE
	cout << "INORDER TRAVERSE OF TREE: ";
	inorder_traverse(root);
	cout << endl;

	cout << "PREORDER TRAVERSE OF TREE: ";
	preorder_traverse(root);
	cout << endl;

	cout << "POSTORDER TRAVERSE OF TREE: ";
	postorder_traverse(root);
	cout << endl;

	return 0;
}

/* PLACE LEAF METHOD
** THIS FUNCTION WILL PLACE A NEW LEAF NODE INTO THE BINARY TREE BASED ON THE VALUE OF THE
** OTHER NODES AND THE CURRENT TREE STRUCTURE. I PLANNED TO USE A WHILE LOOP FOR THIS PART
** OF THE PROJECT TO AVOID RECURSION; EVEN THOUGH THIS REQUIRES AN EXTRA VARIABLE AND A FEW
** MORE LINES OF CODE, WHILE LOOPS MAY HELP THIS RUN A LITTLE FASTER.
** INPUT: node* leaf (a new leaf node), node* root (the root node of the tree)
** OUTPUT: none (void function)
*/
void place_leaf(node* leaf, node* root) {
	bool placed = false;

	node* current = root;

	while (placed == false) {
		if (leaf->value < current->value) {
			if (current->prev != NULL) {
				current = current->prev;
			} else {
				current->prev = leaf;
				placed = true;
			}
		} else if (leaf->value > current->value) {
			if (current->next != NULL) {
				current = current->next;
			} else {
				current->next = leaf;
				placed = true;
			}
		}
	}
}

/* INORDER TRAVERSE METHOD
** THIS METHOD TRAVERSES THE BINARY TREE IN INCREASING ORDER. THE EXACT ALGORITHM IS:
** 1. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER VALUES OF A SUBTREE)
** 2. PRINT THE CURRENT NODE IN EACH RECURSIVE METHOD (THIS WILL MEAN THE LAST RECURSIVE METHOD
** FROM THE PREVIOUS NODES WILL PRINT FIRST)
## 3. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
** INPUT: node* head (head node of the tree [basically the root])
** OUTPUT: none (void function)
*/
void inorder_traverse(node* head) {
	if(head == nullptr) {
		return;
	}

	inorder_traverse(head->prev);

	cout << head->value << " ";

	inorder_traverse(head->next);
}

/* PREORDER TRAVERSE METHOD
** THIS METHOD TRAVERSES THE BINARY TREE FROM THE ROOT NODE FIRST, AND THEN VISITS THE LEAF NODES.
** ODDLY ENOUGH, THE PREORDER TRAVERSAL WILL PROVIDE A LIST OF THE TREE ELEMENTS THAT, IF INPUTTED
** AGAIN, WILL PRODUCE AN EXACT COPY OF THE BINARY TREE! THE EXACT ALGORITHM IS:
** 1. PRINT THE CURRENT NODE (ROOT IS THE FIRST TO PRINT, AND LEAF NODES AFTER)
** 2. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER NODES OF A SUBTREE)
## 3. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
** INPUT: node* head (head node of the tree [basically the root])
** OUTPUT: none (void function)
*/
void preorder_traverse(node* head) {
	if(head == nullptr) {
		return;
	}

	cout << head->value << " ";

	preorder_traverse(head->prev);

	preorder_traverse(head->next);
}

/* POSTORDER TRAVERSE METHOD
** THIS METHOD TRAVERSES THE BINARY TREE FROM THE BOTTOM NODES OF EACH SUBTREE FIRST, AND THEN 
** VISITS THE ROOT NODE LAST. THE EXACT ALGORITHM IS:
** 1. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER NODES OF A SUBTREE)
## 2. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
** 3. PRINT THE CURRENT NODE IN EACH RECURSIVE METHOD (THIS MEANS AFTER RECURSION, THE BOTTOM LEAVES
** OF EACH SUBTREE WILL PRINT FIRST AND THE ROOT NODE WILL BE THE LAST)
** INPUT: node* head (head node of the tree [basically the root])
** OUTPUT: none (void function)
*/
void postorder_traverse(node* head) {
	if(head == nullptr) {
		return;
	}

	postorder_traverse(head->prev);

	postorder_traverse(head->next);

	cout << head->value << " ";
}