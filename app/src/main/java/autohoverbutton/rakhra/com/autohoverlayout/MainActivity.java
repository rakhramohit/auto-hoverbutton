package autohoverbutton.rakhra.com.autohoverlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import autohoverbutton.rakhra.com.libautohover.AutoHoverButton;

public class MainActivity extends AppCompatActivity {

    AutoHoverButton tab1, tab2, tab3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab1 = (AutoHoverButton) findViewById(R.id.tab1);
        tab2 = (AutoHoverButton) findViewById(R.id.tab2);
        tab3 = (AutoHoverButton) findViewById(R.id.tab3);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeAllUnselectable();
                tab1.makeSelectable();
            }
        });


        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeAllUnselectable();
                tab2.makeSelectable();

            }
        });


        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeAllUnselectable();
                tab3.makeSelectable();
            }
        });


    }

    private void makeAllUnselectable()
    {
        tab1.makeUnSelectable();
        tab2.makeUnSelectable();
        tab3.makeUnSelectable();
    }
}
