package lss.com.presenter;

import lss.com.modle.MyModle;
import lss.com.view.IPersonView;

public class MyPresenter {
    private MyModle myModle;
    private IPersonView personView;

    public MyPresenter(IPersonView personView){
        this.personView = personView;
        myModle = new MyModle();
    }

    public void load(){
        String name = myModle.getName("lishuisheng");
        personView.setData(name);
    }
}
