import axios from 'axios';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

export const ACTION_TYPES = {
  FETCH_SUBJECT: 'subject/FETCH_SUBJECT',
  FETCH_SUBJECT_TEACHERS: 'subject/FETCH_SUBJECT_TEACHERS',
  SET_OFFSET: 'subject/SET_OFFSET'
};

const initialState = {
  subject: {
    id: '0',
    title: 'Fundamentele programarii',
    description: 'Cursul domnului Istvan, foarte smecher',
    semester: 1,
    section: 'Informatica',
    language: 'Romana',
    type: 'Obligatoriu'
  },
  teachers: [],
  fetchSuccess: false,
  fetchTeachersSuccess: false,
  offset: 0
};

export type SubjectState = Readonly<typeof initialState>;

// Reducer
export default (state: SubjectState = initialState, action): SubjectState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SUBJECT):
      return {
        ...state,
        fetchSuccess: false
      };
    case FAILURE(ACTION_TYPES.FETCH_SUBJECT):
      return {
        ...state,
        fetchSuccess: false
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBJECT):
      return {
        ...state,
        fetchSuccess: true,
        subject: action.payload.data
      };
    case REQUEST(ACTION_TYPES.FETCH_SUBJECT_TEACHERS):
      return {
        ...state,
        fetchTeachersSuccess: false
      };
    case FAILURE(ACTION_TYPES.FETCH_SUBJECT_TEACHERS):
      return {
        ...state,
        fetchTeachersSuccess: false,
        teachers: []
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBJECT_TEACHERS):
      return {
        ...state,
        fetchTeachersSuccess: true,
        teachers: action.payload.data
      };
    case ACTION_TYPES.SET_OFFSET:
      return {
        ...state,
        offset: action.payload
      };
    default:
      return state;
  }
};
export const fetchSubject = id => ({
  type: ACTION_TYPES.FETCH_SUBJECT,
  payload: axios.get('ourApi/subjects/' + id)
});
export const fetchSubjectTeachers = id => ({
  type: ACTION_TYPES.FETCH_SUBJECT_TEACHERS,
  payload: axios.get('ourApi/subjects/' + id + '/teachers')
});
export const setOffset = (event, offset) => ({
  type: ACTION_TYPES.SET_OFFSET,
  payload: offset
});

// Actions
