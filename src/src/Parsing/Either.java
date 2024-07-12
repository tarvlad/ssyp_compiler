package Parsing;

import java.util.Optional;

public class Either<R,L> {
    private L left;
    private R right;

    private static <L,R> Either<L,R> left(L left) {
        return new Either(left, null);
    }
    private static <L,R> Either<L,R> right(R right) {
        return new Either(null, right);
    }
    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public Optional<L> getLeft() {
        return Optional.ofNullable(this.left);
    }

    public Optional<R> getRight() {
        return Optional.ofNullable(this.right);
    }
}
