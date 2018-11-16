package lss.com.mvptest;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lss.com.presenter.MyPresenter;
import lss.com.view.IPersonView;

public class MainActivity extends AppCompatActivity implements IPersonView {

    private Button button;
    private TextView textView;
    private MyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.tv);
        presenter = new MyPresenter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(presenter != null){
                   presenter.load();
               }
            }
        });
    }

    @Override
    public void setData(String string) {
        textView.setText(string);
    }
}
