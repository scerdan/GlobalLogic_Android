package com.example.exercise.interfaces;

import com.example.exercise.models.NotebookModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("list")
    Call <List<NotebookModel>> obtenerListado();
}
