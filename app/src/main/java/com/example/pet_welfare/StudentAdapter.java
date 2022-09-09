package com.example.pet_welfare;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    Context context;
    List<StudentModel> studentModelList;

    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener{
        void OnItemClick(int position);
    }

    public void OnRecyclerViewClickListener(OnRecyclerViewClickListener listener){
        this.listener=listener;
    }



    ImageView img;

    public StudentAdapter(Context context, List<StudentModel> studentModelList) {
        this.context = context;
        this.studentModelList = studentModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row,parent,false);

        return new ViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        StudentModel studentModel=studentModelList.get(position);





        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.name.getContext(),Target.class);
                intent.putExtra("dname",studentModelList.get(position).getName());
                intent.putExtra("dbreed",studentModelList.get(position).getBreed());
                intent.putExtra("dwei",studentModelList.get(position).getWeight());
                intent.putExtra("dage",studentModelList.get(position).getAge());
                intent.putExtra("dcat",studentModelList.get(position).getCategory());
                intent.putExtra("dgen",studentModelList.get(position).getGender());
                intent.putExtra("oname",studentModelList.get(position).getOwnerName());
                intent.putExtra("onumb",studentModelList.get(position).getOwnerPhoneNumber());
                intent.putExtra("adopp",studentModelList.get(position).getPricePerYear());
                intent.putExtra("dimage",studentModelList.get(position).getImage());
                intent.putExtra("downer",studentModelList.get(position).getOwnerName());
                intent.putExtra("dphn",studentModelList.get(position).getOwnerPhoneNumber());
                intent.putExtra("buyp",studentModelList.get(position).getBuyingPrice());
                intent.putExtra("adoptp",studentModelList.get(position).getPricePerYear());
                intent.putExtra("daddr",studentModelList.get(position).getAddress());
                intent.putExtra("dvacc",studentModelList.get(position).getVaccinated());
                intent.putExtra("dvaccno",studentModelList.get(position).getVaccinatedNo());
                intent.putExtra("dimage",studentModelList.get(position).getImage());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.name.getContext().startActivity(intent);

            }
        });




        holder.name.setText("Name : "+studentModel.getName());
        holder.gender.setText("Gender : "+studentModel.getGender());
        holder.breed.setText("Breed : "+studentModel.getBreed());


        Glide.with(context).load(studentModelList.get(position).getImage()).into(holder.image);




    }

    @Override
    public int getItemCount() {
        return studentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name,breed,gender;
        public ViewHolder(@NonNull View itemView,OnRecyclerViewClickListener listener) {
            super(itemView);


            image=itemView.findViewById(R.id.imageView4);
            name=itemView.findViewById(R.id.textView15);
            breed=itemView.findViewById(R.id.textView16);
            gender=itemView.findViewById(R.id.textView17);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null && getAbsoluteAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAbsoluteAdapterPosition());
                    }
                }
            });



        }
    }
}
