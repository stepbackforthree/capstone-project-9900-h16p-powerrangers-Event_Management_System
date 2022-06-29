import React from 'react';
// import { makeStyles } from '@material-ui/core/styles';
import { categoryList, ratingList } from '../../../constants';
import CheckboxProton from '../../CheckboxProton';
import FilterListToggle from '../../FilterListToggle';
import SliderProton from '../../SliderProton';
import './styles.css';



export default function FilterPanel({
  selectedCategory,
  selectToggle,
  selectedRating,
  selectRating,
  cities,
  changeChecked,
  selectedPrice,
  changePrice
}) {
  return (
    <div>
      <div className="input-group">
        {/* Category */}
        <p className="label">Category</p>
        <FilterListToggle 
          options={categoryList} 
          value={selectedCategory} 
          selectToggle={selectToggle} 
        />
      </div>

      {/* City */}
      <div className="input-group">
        <p className="label">Cities</p>
        {cities.map(city=>(
          <CheckboxProton 
            key={city.id} 
            city={city} 
            changeChecked={changeChecked}
            />
          ))}
      </div>
      {/* Price Range */}
      <div className="input-group">
        <p className="label-range">Price Range</p>
        <SliderProton value={selectedPrice} changedPrice={changePrice} />
      </div>

      {/* Star Rating */}
      <div className="input-group">
        <p className="label">Star Rating</p>
        <FilterListToggle 
          options={ratingList} 
          value={selectedRating} 
          selectToggle={selectRating} 
        />
      </div>

    </div>
  )
}
