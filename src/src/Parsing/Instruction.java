package Parsing;

import java.util.Optional;

public interface Instruction {
    InstructionType type();
    Optional<Either<Variable, Integer>> get(int index);
}
