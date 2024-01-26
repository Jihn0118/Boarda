import { combineReducers } from 'redux';
import moimReducer from './moim';

const rootReducer = combineReducers({
  moim: moimReducer,
  // 다른 리듀서들...
});

export default rootReducer;