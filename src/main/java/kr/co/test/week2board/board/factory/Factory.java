package kr.co.test.week2board.board.factory;

// 제품 객체 추상화 (인터페이스)
interface Factory {
    void render();
}

// 제품 구현체
class ConcreteProductA implements Factory {
    public void render() {
        System.out.println("상품 A");
    }
}

class ConcreteProductB implements Factory {
    public void render() {
        System.out.println("상품 B");
    }
}