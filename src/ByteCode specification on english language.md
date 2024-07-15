# ByteCode Specification
Bytecode is a set of instructions that will be used to work with a stack, the elements of which are accessed through pointers to cells in memory. The following are the instructions for working with the stack.
**ADD**
- Arguments: dst, src_1, src_2.
- Description: Adding the sum of src_1 and src_2 to the stack cell under the dst pointer.
- Example: ADD 1 2 3

**SUB**
- Arguments: dst, src_1, src_2.
- Description: Adding the difference between src_1 and src_2 to the stack cell under the dst pointer.
- Example: SUB 1 2 3

**MUL**
- Arguments: dst, src_1, src_2.
- Description: Adding the product of src_1 and src_2 to the stack cell under the dst pointer.
- Example: MUL 1 2 3

**DIV**
- Arguments: dst, src_1, src_2.
- Description: Adding a division of src_1 by src_2 to the stack cell under the dst pointer.
- Example: DIV 1 2 3

**SET**
- Arguments: dst_off, number
- Description: Changes the element in the cell under the dst_off pointer to number.
- Example: SET 1 2

**RETURN**
- Arguments: offset
- Description: Returns the element in the cell at offset
- Example: RETURN 1

**CALL**
- Arguments: name, off
- Description: Calls a function named name, the result of which will be written to the cell under index off
- Example: CALL be 1

**MOV**
- Arguments: dst_off, src_off
- Description: Moves the element from the cell under the dst_off pointer to the cell under the src_off pointer.
- Example: MOV 1 2

**JMP**
- Arguments: cmp_type, offsetRight, offsetLeft, destination
- Description: Applies a comparison of offsetRight to offsetLeft using the cmp_type operator (>, <, >=, <=, ==, !=) and, if true, starts compilation from the cell under destination.
- Example: JMP < 1 2 3