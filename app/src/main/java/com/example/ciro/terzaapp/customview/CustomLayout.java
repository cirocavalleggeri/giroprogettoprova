package com.example.ciro.terzaapp.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CustomLayout extends ViewGroup {

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //ogni volta che lo schermo cambia dobbiamo calcolare le dimensioni per le finestre contenute
        // per poter stabilire le sue dimensioni.Partiamo dal lato in alto a sinistra
        //poi calcoliamo le altezze e le larghezze delle finestre contenute
        //la somma delle altezze delle finestre contenute ci darà l'altezza definitiva del contenitore.
      int numero_di_finestre_contenute=getChildCount();
        Log.d("VIEW","onLayout numero_di_finestre_contenute:"+numero_di_finestre_contenute);
      //il lato sinistro
      int lato_sinistro=l+getPaddingLeft();
      int lato_in_alto_a_sinistra=t+getPaddingTop();
      int altezzaRiga=0;//sarò l'altezza massima delle finestre figlio sul rigo
      for(int i=0;i<numero_di_finestre_contenute;i++){
          View finestraFiglio=getChildAt(i);
          int finestraFiglioLarghezza=finestraFiglio.getMeasuredWidth();
          int finestraFiglioAltezza=finestraFiglio.getMeasuredHeight();
          Log.d("VIEW","onLayout finestraFiglioLarghezza:"+finestraFiglioLarghezza);
          Log.d("VIEW","onLayout finestraFiglioAltezza:"+finestraFiglioAltezza);
          //aggiungiamo piu' finestre figlio alla riga,se la larghezza delle finestre
          //supera la larghezza del contenitore -r- si va alla riga successiva
          //e ci si sposta dell'altezza massima delle finestre figlio sulla riga
          //considerando anche il padding di destro
          //controlliamo la larghezza della riga:
          if(lato_sinistro+finestraFiglioLarghezza<r-getPaddingRight()){
              finestraFiglio.layout(lato_sinistro,lato_in_alto_a_sinistra,lato_sinistro+finestraFiglioLarghezza,lato_in_alto_a_sinistra+finestraFiglioAltezza);
              lato_sinistro=lato_sinistro+finestraFiglioLarghezza;

          }else { lato_sinistro=l+getPaddingLeft();
                  lato_in_alto_a_sinistra=lato_in_alto_a_sinistra+altezzaRiga;
                  finestraFiglio.layout(lato_sinistro,lato_in_alto_a_sinistra,lato_sinistro+finestraFiglioLarghezza,lato_in_alto_a_sinistra+finestraFiglioAltezza);
                  altezzaRiga=0;
                  lato_sinistro=lato_sinistro+finestraFiglioLarghezza;
          }
          //l'altezza della riga del contenitore è il massimo delle altezze delle finestre figlio
          if(finestraFiglioAltezza>altezzaRiga){altezzaRiga=finestraFiglioAltezza;}

      }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //se la larghezza della finestra è zero è inutile fare calcoli perchè la finestra non si vedrà
        if(getWidth() == 0) {
            Log.d("VIEW","onMesure getWidth():"+getWidth());
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
           // return;
        }
        int numero_di_finestre_contenute=getChildCount();
        Log.d("VIEW","onMeasure numero_di_finestre_contenute:"+numero_di_finestre_contenute);
        int altezzaRiga=0;
        //qui calcoliamo l'altezza e la larghezza in base alle finestre figlio
        int massimaLarghezzaRiga=0;
        int massimaAltezzaContenitore=0;
        int lato_sinistro=0;
        int lato_in_alto_a_sinistra=0;
        //iteriamo le finestre interne
        for(int i=0;i<numero_di_finestre_contenute;i++){
            View singola_finestra_interna=getChildAt(i);
            measureChild(singola_finestra_interna, widthMeasureSpec, heightMeasureSpec);
            //ricaviamoci le dimensioni della finestra soprastante
            int finestraFiglioAltezza=singola_finestra_interna.getMeasuredHeight();
            int finestraFiglioLarghezza=singola_finestra_interna.getMeasuredWidth();

            Log.d("VIEW","onMesure finestraFiglioLarghezza:"+finestraFiglioLarghezza);
            Log.d("VIEW","onMesure finestraFiglioAltezza:"+finestraFiglioAltezza);
            if(lato_sinistro+finestraFiglioLarghezza<getWidth()){
                lato_sinistro=lato_sinistro+finestraFiglioLarghezza;
            }else {
                if(lato_sinistro>massimaLarghezzaRiga){massimaLarghezzaRiga=lato_sinistro;}
                //vai a riga successiva
                lato_sinistro=0;
                lato_in_alto_a_sinistra=lato_in_alto_a_sinistra+finestraFiglioAltezza;
                altezzaRiga=0;
            }
            //l'altezza della riga è il massimo delle altezze
            if(finestraFiglioAltezza>altezzaRiga){altezzaRiga=finestraFiglioAltezza;}
        }
        if(lato_sinistro>massimaLarghezzaRiga){massimaLarghezzaRiga=lato_sinistro;}//così abbiamo ricalcolato la larghezza
        //del contenitore
        massimaAltezzaContenitore=lato_in_alto_a_sinistra+altezzaRiga;//così abbiamo calcolato l'altezza del contenitore
        //diamo finalmente la nuova misura del Layout contenitore
        setMeasuredDimension(getMeasure(widthMeasureSpec, massimaLarghezzaRiga), getMeasure(heightMeasureSpec,   massimaAltezzaContenitore));
    }

    private int getMeasure(int spec, int desired) {
        switch(MeasureSpec.getMode(spec)) {
            case MeasureSpec.EXACTLY:
                return MeasureSpec.getSize(spec);

            case MeasureSpec.AT_MOST:
                return Math.min(MeasureSpec.getSize(spec), desired);

            case MeasureSpec.UNSPECIFIED:
            default:
                return desired;
        }
    }
    public void ridisegna(){
        invalidate();
    }
}
