''' RYAN SLATTERY
*** BINARY TREE CONSTRUCTION AND TRAVERSAL (PYTHON)
*** MAY 13, 2023
*** PURPOSE: I'M PROBABLY THE MOST FAMILIAR WITH PYTHON PROGRAMMING, SO I CANNOT SAY THIS IS REALLY
*** A REFRESHER FOR ME IN A WAY THAT THE C++ AND JAVA BINARY TREE PROGRAMS WERE. HOWEVER, I CAN SAY
*** THAT BECAUSE OF PYTHON'S UNIQUE SYNTAX AND DIFFERENCES FROM THOSE TWO OTHER LANGUAGES, BUILDING 
*** THIS PROGRAM REQUIRED SOME RESOURCEFUL THINKING!
*** NOTES:
*** 1. DO NOT ENTER DUPLICATE VALUES!!! I DID NOT SET BOUNDARIES TO HANDLE THAT. THIS IS MEANT TO 
*** TAKE A "SET" OF NUMBERS (NO DUPLICATES). YOU WILL END UP IN AN INFINITE LOOP IF YOU DO.
*** 2. -1 IS USED TO END THE LOOP. THIS VALUE WILL NOT BE INCLUDED IN THE TREE.
*** 3. THE TRAVERSAL METHODS HAD TO INCLUDE THE INPUT AND RETURN OF A PYTHON LIST TO KEEP ALL OUTPUTS
*** ON THE SAME LINES.
'''

''' NODE CLASS
*** ONLY CONTAINS THE CONSTRUCTOR WHICH INITIALIZES value, next, AND prev.
*** INPUT: input_num (AN INPUT NUMBER GIVEN BY THE USER)
*** OUTPUT: NONE
'''
class Node:
	def __init__(self, input_num):
		self.value = input_num
		self.next = None
		self.prev = None

''' INORDER TRAVERSE METHOD
*** THIS METHOD TRAVERSES THE BINARY TREE IN INCREASING ORDER. THE EXACT ALGORITHM IS:
*** 1. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER VALUES OF A SUBTREE)
*** 2. ADD THE CURRENT NODE IN EACH RECURSIVE METHOD (THIS WILL MEAN THE NODE ON THE BOTTOM-LEFT
*** SIDE OF THE TREE WILL BE THE FIRST ADDED, AND THE NODE ON THE BOTTOM RIGHT WILL BE THE LAST)
*** 3. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
*** INPUT: head (BASICALLY THE ROOT NODE), traversal_list (A LIST OF NODES **INITIALLY EMPTY**)
*** OUTPUT: traversal_list
'''
def inorder_traverse(head, traversal_list):
	if (head == None):
		return

	inorder_traverse(head.prev, traversal_list)
	traversal_list.append(head.value)
	inorder_traverse(head.next, traversal_list)

	return traversal_list

''' PREORDER TRAVERSE METHOD
*** THIS METHOD TRAVERSES THE BINARY TREE FROM THE ROOT NODE FIRST, AND THEN VISITS THE LEAF NODES.
*** ODDLY ENOUGH, THE PREORDER TRAVERSAL WILL PROVIDE A LIST OF THE TREE ELEMENTS THAT, IF INPUTTED
*** AGAIN, WILL PRODUCE AN EXACT COPY OF THE BINARY TREE! THE EXACT ALGORITHM IS:
*** 1. ADD THE CURRENT NODE TO THE LIST (THE ROOT IS THE FIRST TO ADD, AND LEAF NODES AFTER)
*** 2. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER NODES OF A SUBTREE)
*** 3. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
*** INPUT: head (BASICALLY THE ROOT NODE), traversal_list (A LIST OF NODES **INITIALLY EMPTY**)
*** OUTPUT: traversal_list
'''
def preorder_traverse(head, traversal_list):
	if (head == None):
		return

	traversal_list.append(head.value)
	preorder_traverse(head.prev, traversal_list)
	preorder_traverse(head.next, traversal_list)

	return traversal_list

''' POSTORDER TRAVERSE METHOD
*** THIS METHOD TRAVERSES THE BINARY TREE FROM THE BOTTOM NODES OF EACH SUBTREE FIRST, AND THEN 
*** VISITS THE ROOT NODE LAST. THE EXACT ALGORITHM IS:
*** 1. RECURSIVELY GO THROUGH THE PREVIOUS NODES (SMALLER NODES OF A SUBTREE)
*** 2. RECURSIVELY GO THROUGH THE NEXT NODES (LARGER VALUES OF A SUBTREE)
*** 3. ADD THE CURRENT NODE IN EACH RECURSIVE METHOD TO THE LIST (THIS MEANS AFTER RECURSION, THE 
*** BOTTOM LEAVES OF EACH SUBTREE WILL BE THE FIRST IN THE LIST, AND THE ROOT NODE WILL BE THE LAST)
*** INPUT: head (BASICALLY THE ROOT NODE), traversal_list (A LIST OF NODES **INITIALLY EMPTY**)
*** OUTPUT: traversal_list
'''
def postorder_traverse(head, traversal_list):
	if (head == None):
		return

	postorder_traverse(head.prev, traversal_list)
	postorder_traverse(head.next, traversal_list)
	traversal_list.append(head.value)

	return traversal_list

''' PLACE LEAF METHOD
*** THIS FUNCTION WILL PLACE A NEW LEAF NODE INTO THE BINARY TREE BASED ON THE VALUE OF THE
*** OTHER NODES AND THE CURRENT TREE STRUCTURE.
*** INPUT: leaf (LEAF NODE), root (ROOT NODE)
*** OUTPUT: NONE
'''
def place_leaf(leaf, root):
	current = root
	parent = current

	while(current != None):
		if (leaf.value < current.value):
			parent = current
			current = current.prev
		elif (leaf.value > current.value):
			parent = current
			current = current.next

	if (leaf.value < parent.value):
		parent.prev = leaf
	elif (leaf.value > parent.value):
		parent.next = leaf

''' BINARY TREE FUNCTION
*** THIS FUNCTION ACTS AS THE "MAIN" METHOD OF THE PROGRAM. IT PROMPTS THE USER TO INPUT NUMBERS TO
*** ENTER INTO THE BINARY TREE. IT CALLS UPON THE place_leaf() METHOD FOR EACH INPUT TO CONSTRUCT NEW
*** LEAVES IN THE TREE AND EACH TRAVERSAL METHOD TO PRINT.
*** INPUT: NONE
*** OUTPUT: NONE
'''
def binarytree():
	input_num = int(input("ENTER THE FIRST NUMBER: "))

	root = Node(input_num)

	while (input_num != -1):
		input_num = int(input("ENTER THE NEXT NUMBER (OR ENTER -1 TO END): "))

		if (input_num == -1):
			break

		leaf = Node(input_num)

		place_leaf(leaf, root)

	# OUTPUTTING THE DIFFERENT TRAVERSALS OF THE BINARY TREE
	inorder_list = inorder_traverse(root, [])
	print("INORDER TRAVERSE OF TREE: ", inorder_list)

	preorder_list = preorder_traverse(root, [])
	print("PREORDER TRAVERSE OF TREE: ", preorder_list)

	postorder_list = postorder_traverse(root, [])
	print("POSTORDER TRAVERSE OF TREE: ", postorder_list)


# CALLING THE binarytree() FUNCTION SO THE PROGRAM CAN RUN
binarytree()


