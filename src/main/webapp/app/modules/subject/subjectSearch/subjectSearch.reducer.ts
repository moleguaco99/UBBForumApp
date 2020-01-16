import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

export const ACTION_TYPES = {
  SHOW_MODAL: 'subjectSearch/SHOW_MODAL',
  HIDE_MODAL: 'subjectSearch/HIDE_MODAL',
  FETCH_SUBJECTS: 'subjectSearch/FETCH_SUBJECTS',
  CHANGE_SECTION: 'subjectSearch/CHANGE_SECTION',
  CHANGE_SEMESTER: 'subjectSearch/CHANGE_SEMESTER',
  SEARCH_SUBJECT: 'subjectSearch/SEARCH_SUBJECT',
  RESET_FORM: 'subjectSearch/RESET_FORM'
};

const initialState = {
  isModalShown: false,
  subjects: [],
  subjectsBySection: [],
  subjectsBySemester: [],
  sectionOptions: [],
  semesterOptions: [],
  subjectOptions: [],
  isSemesterDisabled: true,
  isSubjectDisabled: true,
  subjectID: 0
};

export type SubjectSearchState = Readonly<typeof initialState>;

const getSectionOptions = subjects => {
  /* eslint-disable no-console */
  console.log(subjects);
  const hashMap = {};
  const options = [];
  for (let i = 0; i < subjects.length; i++) {
    const section = subjects[i].section + ' ' + subjects[i].language;
    if (hashMap[section] !== true) {
      options.push(section);
      hashMap[section] = true;
    }
  }
  return options;
};

const getSemesterOptions = subjects => {
  const hashMap = {};
  const options = [];
  for (let i = 0; i < subjects.length; i++) {
    if (hashMap[subjects[i].semester] !== true) {
      options.push(subjects[i].semester);
      hashMap[subjects[i].semester] = true;
    }
  }
  return options;
};
const getSubjectOptions = subjects => {
  const options = [];
  const hashMap = {};
  for (let i = 0; i < subjects.length; i++) {
    if (hashMap[subjects[i].title] !== true) {
      options.push(subjects[i].title);
      hashMap[subjects[i].title] = true;
    }
  }
  return options;
};
const filterBySection = (selection, subjects) => {
  const selected = selection.split(' ');
  const filtered = [];
  for (let i = 0; i < subjects.length; i++) {
    if (subjects[i].section === selected[0] && subjects[i].language === selected[1]) filtered.push(subjects[i]);
  }
  return filtered;
};

const filterBySemester = (selection, subjects) => {
  const filtered = [];
  for (let i = 0; i < subjects.length; i++) {
    if (subjects[i].semester === parseInt(selection, 10)) filtered.push(subjects[i]);
  }
  return filtered;
};

const getIDByTitle = (selection, subjects) => {
  for (let i = 0; i < subjects.length; i++) {
    if (subjects[i].title === selection) return subjects[i].idSubject;
  }
  return 0;
};

// Reducer
export default (state: SubjectSearchState = initialState, action): SubjectSearchState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SUBJECTS):
    case FAILURE(ACTION_TYPES.FETCH_SUBJECTS):
      return {
        ...state,
        subjects: [],
        subjectsBySection: []
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBJECTS):
      return {
        ...state,
        subjects: action.payload,
        subjectsBySection: action.payload,
        sectionOptions: getSectionOptions(action.payload)
      };
    case ACTION_TYPES.CHANGE_SECTION:
      return {
        ...state,
        subjectsBySection: filterBySection(action.payload, state.subjects),
        semesterOptions: getSemesterOptions(filterBySection(action.payload, state.subjects)),
        isSemesterDisabled: false,
        isSubjectDisabled: true
      };
    case ACTION_TYPES.CHANGE_SEMESTER:
      return {
        ...state,
        subjectsBySemester: filterBySemester(action.payload, state.subjectsBySection),
        subjectOptions: getSubjectOptions(filterBySemester(action.payload, state.subjectsBySection)),
        isSubjectDisabled: false
      };
    case ACTION_TYPES.SEARCH_SUBJECT:
      return {
        ...state,
        isModalShown: false,
        subjectID: getIDByTitle(action.payload, state.subjectsBySemester)
      };
    case ACTION_TYPES.SHOW_MODAL:
      return {
        ...state,
        isModalShown: true
      };
    case ACTION_TYPES.HIDE_MODAL:
      return {
        ...state,
        isModalShown: false
      };
    case ACTION_TYPES.RESET_FORM:
      return {
        ...state,
        isModalShown: false,
        subjects: [],
        subjectsBySection: [],
        subjectsBySemester: [],
        sectionOptions: [],
        semesterOptions: [],
        subjectOptions: [],
        isSemesterDisabled: true,
        isSubjectDisabled: true,
        subjectID: 0
      };
    default:
      return state;
  }
};
export const resetForm = () => ({
  type: ACTION_TYPES.RESET_FORM
});
export const searchSubject = title => ({
  type: ACTION_TYPES.SEARCH_SUBJECT,
  payload: title
});
export const changeSemester = semester => ({
  type: ACTION_TYPES.CHANGE_SEMESTER,
  payload: semester
});
export const changeSection = section => ({
  type: ACTION_TYPES.CHANGE_SECTION,
  payload: section
});
export const fetchSubjects = () => ({
  type: ACTION_TYPES.FETCH_SUBJECTS,
  payload: fetch('http://localhost:8080/ourApi/subjects').then(response => response.json())
});
export const showModal = () => ({
  type: ACTION_TYPES.SHOW_MODAL
});
export const hideModal = () => ({
  type: ACTION_TYPES.HIDE_MODAL
});

// Actions
