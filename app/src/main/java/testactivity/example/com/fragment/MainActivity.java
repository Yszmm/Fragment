package testactivity.example.com.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(this);
        //replaceFragment(new right_fragment());
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.button:
//               // replaceFragment(new Anotheright_fragment());
//                break;
//            default:
//                break;
//        }
//    }
//    private void replaceFragment (Fragment fragment)
//    {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.right_layout,fragment);
//        transaction.addToBackStack(null); // 将一个事物添加到返回栈中
//        transaction.commit();
//    }
}
