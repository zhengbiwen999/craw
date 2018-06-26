package factory.easyFactory.server;

/**
 * Created by zbww on 2018/6/26.
 */
public class OperationFactory {

    public static Operation creatoreOperation(char operate){

        Operation operation = null;

        switch (operate){
            case '+':
                operation = new OperationAdd();
                break;
            case '-':
                operation = new OperationSub();
                break;
        }
        return operation;

    }

}
