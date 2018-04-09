package ke.co.zivish.smartsally;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 8/19/2017.
 */

public class BookingAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public BookingAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Booking object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        BookingHolder bookingHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            bookingHolder = new BookingHolder();
            bookingHolder.tvBookingNo =(TextView) row.findViewById(R.id.tvBookingNo);
            bookingHolder.tvCustomerName =(TextView) row.findViewById(R.id.tvCustomerName);
            bookingHolder.tvServiceName =(TextView) row.findViewById(R.id.tvServiceName);
            bookingHolder.tvTime =(TextView) row.findViewById(R.id.tvTime);
            row.setTag(bookingHolder);

        }
        else {
            bookingHolder = (BookingHolder) row.getTag();
        }

        Booking booking = (Booking) this.getItem(position);
        bookingHolder.tvBookingNo.setText(booking.getBooking_no());
        bookingHolder.tvCustomerName.setText(booking.getCustomer_name());
        bookingHolder.tvServiceName.setText(booking.getService_name());
        bookingHolder.tvTime.setText(booking.getTime());
        return row;
    }

    static class BookingHolder{
        TextView tvBookingNo,tvCustomerName,tvServiceName,tvTime;
    }
}
