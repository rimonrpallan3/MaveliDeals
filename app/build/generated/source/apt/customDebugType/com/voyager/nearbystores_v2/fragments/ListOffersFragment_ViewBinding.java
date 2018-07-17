// Generated code from Butter Knife. Do not modify!
package com.voyager.nearbystores_v2.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.voyager.nearbystores_v2.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListOffersFragment_ViewBinding implements Unbinder {
  private ListOffersFragment target;

  private View view2131756744;

  private View view2131756687;

  private View view2131756686;

  private View view2131756692;

  private View view2131756746;

  private View view2131756745;

  @UiThread
  public ListOffersFragment_ViewBinding(final ListOffersFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.closeFilterTitle, "field 'closeFilterTitle' and method 'closeTitleFilters'");
    target.closeFilterTitle = Utils.castView(view, R.id.closeFilterTitle, "field 'closeFilterTitle'", TextView.class);
    view2131756744 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.closeTitleFilters();
      }
    });
    view = Utils.findRequiredView(source, R.id.revertFilterTxt, "field 'revertFilterTxt' and method 'backToMainFilter'");
    target.revertFilterTxt = Utils.castView(view, R.id.revertFilterTxt, "field 'revertFilterTxt'", ImageView.class);
    view2131756687 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.backToMainFilter();
      }
    });
    view = Utils.findRequiredView(source, R.id.closeFiltersImg, "field 'closeFiltersImg' and method 'closeFiltersImg'");
    target.closeFiltersImg = Utils.castView(view, R.id.closeFiltersImg, "field 'closeFiltersImg'", ImageView.class);
    view2131756686 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.closeFiltersImg();
      }
    });
    view = Utils.findRequiredView(source, R.id.applyBtnFilter, "field 'applyBtnFilter' and method 'applyInnerFilters'");
    target.applyBtnFilter = Utils.castView(view, R.id.applyBtnFilter, "field 'applyBtnFilter'", Button.class);
    view2131756692 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.applyInnerFilters();
      }
    });
    view = Utils.findRequiredView(source, R.id.applyFilters, "method 'applyFilters'");
    view2131756746 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.applyFilters();
      }
    });
    view = Utils.findRequiredView(source, R.id.applyFiltersImg, "method 'applyFiltersImg'");
    view2131756745 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.applyFiltersImg();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ListOffersFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.closeFilterTitle = null;
    target.revertFilterTxt = null;
    target.closeFiltersImg = null;
    target.applyBtnFilter = null;

    view2131756744.setOnClickListener(null);
    view2131756744 = null;
    view2131756687.setOnClickListener(null);
    view2131756687 = null;
    view2131756686.setOnClickListener(null);
    view2131756686 = null;
    view2131756692.setOnClickListener(null);
    view2131756692 = null;
    view2131756746.setOnClickListener(null);
    view2131756746 = null;
    view2131756745.setOnClickListener(null);
    view2131756745 = null;
  }
}
