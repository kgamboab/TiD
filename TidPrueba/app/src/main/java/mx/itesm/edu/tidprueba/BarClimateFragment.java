package mx.itesm.edu.tidprueba;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.YLabels;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarClimateFragment extends Fragment {


    public BarClimateFragment() {
        // Required empty public constructor
    }

    private BarChart mChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bar_climate, container, false);

        mChart = new BarChart(getActivity());
        mChart.setDescription("");




        mChart.setHighlightIndicatorEnabled(false);
        mChart.setDrawBorder(false);
        mChart.setDrawGridBackground(false);
        mChart.setDrawVerticalGrid(false);
        mChart.setDrawXLabels(false);
        mChart.setDrawYValues(false);
        mChart.setUnit(" €");
        mChart.setDrawBarShadow(false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"emmasophia.ttf");

        mChart.setData(generateBarData(1, 20000, 12));

        Legend l = mChart.getLegend();
        l.setTypeface(tf);

        YLabels labels = mChart.getYLabels();
        labels.setTypeface(tf);

        FrameLayout parent = (FrameLayout) v.findViewById(R.id.contenido);
        parent.addView(mChart);

        return v;
    }


    protected BarData generateBarData(int dataSets, float range, int count) {

        ArrayList<BarDataSet> sets = new ArrayList<BarDataSet>();

        for(int i = 0; i < dataSets; i++) {

            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();


            for(int j = 0; j < count; j++) {
                entries.add(new BarEntry((float) (Math.random() * range) + range / 4, j));
            }

            BarDataSet ds = new BarDataSet(entries, getLabel(i));
            ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
            sets.add(ds);
        }

        BarData d = new BarData(ChartData.generateXVals(0, count), sets);
        return d;
    }

    private String[] mLabels = new String[] { "Company A", "Company B", "Company C", "Company D", "Company E", "Company F" };
    private String getLabel(int i) {
        return mLabels[i];
    }
}
