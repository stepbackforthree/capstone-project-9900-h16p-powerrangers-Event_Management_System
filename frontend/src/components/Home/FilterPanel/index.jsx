import React from 'react';
// import { makeStyles } from '@material-ui/core/styles';
import { categoryList, ratingList } from '../../../constants';
import CheckboxProton from '../../CheckboxProton';
import FilterListToggle from '../../FilterListToggle';
import SliderProton from '../../SliderProton';
import './styles.css';
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';
import { grey } from '@material-ui/core/colors';


const ColorButton = withStyles((theme) => ({
  root: {
    // color: theme.palette.getContrastText(grey[100]),
    color: '#fff',
    backgroundColor: grey[900],
    '&:hover': {
      backgroundColor: grey[700],
    },
  },
}))(Button);


export default function FilterPanel({
  selectedCategory,
  selectToggle,
  selectedRating,
  selectRating,
  cities,
  changeChecked,
  selectedPrice,
  changePrice,
  handleSearchContent
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
      <div className="search-box">
        <ColorButton variant="contained" onClick={handleSearchContent}>Search</ColorButton>
      </div>
      
    </div>
  )
}
