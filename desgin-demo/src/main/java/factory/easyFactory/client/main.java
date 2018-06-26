package factory.easyFactory.client;

import factory.easyFactory.server.Operation;
import factory.easyFactory.server.OperationFactory;

/**
 * Created by zbww on 2018/6/26.
 */
public class main {

    public static void main(String[] args) {
        Operation operation;
        operation = OperationFactory.creatoreOperation('-');

        double result = operation.getResult(1, 2);
        System.out.println(result);
    }

}
