package test.org.kastatest.ui.campaigns

import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import test.org.kastatest.data.entities.Campaign
import test.org.kastatest.R

class CampaignsAdapter : RecyclerView.Adapter<CampaignsAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private var campaigns : List<Campaign> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.campaign_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemView) {
            is CampaignItemView -> holder.itemView.bindTo(campaigns[position], position)
        }
    }

    override fun getItemId(position: Int): Long {
        return campaigns[position].id.toLong()
    }

    override fun getItemCount(): Int {
        return campaigns.size
    }

    fun replaceWith(@Nullable items: List<Campaign>?) {
        if (items == null) {
            this.campaigns = java.util.ArrayList()
        } else {
            this.campaigns = items
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
